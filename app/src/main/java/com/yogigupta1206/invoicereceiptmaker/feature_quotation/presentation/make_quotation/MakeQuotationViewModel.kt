package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItemWithProduct
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.QuotationUseCases
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class MakeQuotationViewModel @Inject constructor(
    private val quotationUseCases: QuotationUseCases,
) : ViewModel() {

    companion object {
        private const val TAG = "MakeQuotationViewModel"
    }

    private val df = DecimalFormat("#")


    private val _makeQuotationState = mutableStateOf(MakeQuotationState())
    val makeQuotationState: State<MakeQuotationState> = _makeQuotationState

    private val _quotationId = mutableLongStateOf(-1L)
    val quotationId: State<Long> = _quotationId

    private val _bottomSheetChargesState = mutableStateOf(BottomSheetChargesState())
    val bottomSheetChargesState: State<BottomSheetChargesState> = _bottomSheetChargesState

    private val _customer = mutableStateOf(Customer())
    val customer: State<Customer> = _customer

    private val _quotationItemList = mutableStateOf(listOf<QuotationItemWithProduct>())
    val quotationItemList: State<List<QuotationItemWithProduct>> = _quotationItemList

    private val _quotation = mutableStateOf(Quotation())
    val quotation: State<Quotation> = _quotation

    private val _totalAmountState = mutableStateOf(TotalAmountState())
    val totalAmountState: State<TotalAmountState> = _totalAmountState

    private val _eventFlow = MutableSharedFlow<UiEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        getQuotationDetails()
    }

    fun onEvent(event: MakeQuotationEvent) {
        when (event) {

            is MakeQuotationEvent.ClickedProductPlusButton -> {
                _eventFlow.tryEmit(UiEvent.OpenProductList)
            }

            is MakeQuotationEvent.DeleteProduct -> {
                viewModelScope.launch(Dispatchers.IO) {
                    quotationUseCases.deleteQuotationItemById(event.quotationItemId)
                }
            }

            is MakeQuotationEvent.EnteredOtherChargesLabel -> {
                _bottomSheetChargesState.value = bottomSheetChargesState.value.copy(
                    otherChargesLabel = event.label
                )
            }

            is MakeQuotationEvent.EnteredOtherChargesTax -> {
                _bottomSheetChargesState.value = bottomSheetChargesState.value.copy(
                    otherChargesTax = event.tax
                )
            }

            is MakeQuotationEvent.EnteredOtherChargesIsTaxable -> {
                _bottomSheetChargesState.value = bottomSheetChargesState.value.copy(
                    otherChargesIsTaxable = event.isTaxable
                )
            }

            is MakeQuotationEvent.EnteredOtherChargesValue -> {
                _bottomSheetChargesState.value = bottomSheetChargesState.value.copy(
                    otherChargesAmount = event.value
                )
            }

            is MakeQuotationEvent.UpdateQuotationDate -> {
                _makeQuotationState.value = makeQuotationState.value.copy(
                    quotationTime = event.date
                )
            }

            MakeQuotationEvent.GenerateQuotation -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        quotationUseCases.generateQuotation(
                            customer.value,
                            quotation.value,
                            quotationItemList.value.map { it.quotationItem },
                            makeQuotationState.value,
                            totalAmountState.value
                        )
                        _eventFlow.tryEmit(UiEvent.SaveQuotationDetails)
                    } catch (e: Exception) {
                        _eventFlow.tryEmit(UiEvent.ShowSnackbar(e.message.toString()))
                    }
                }
            }

            is MakeQuotationEvent.ClickedOtherChargesPlusButton -> {
                _bottomSheetChargesState.value = bottomSheetChargesState.value.copy(
                    otherChargesLabel = makeQuotationState.value.otherChargesLabel,
                    otherChargesAmount = makeQuotationState.value.otherChargesAmount,
                    otherChargesIsTaxable = makeQuotationState.value.otherChargesIsTaxable,
                    otherChargesTax = makeQuotationState.value.otherChargesTax
                )
                _makeQuotationState.value = makeQuotationState.value.copy(
                    showBottomSheet = true
                )
                _eventFlow.tryEmit(UiEvent.ShowBottomSheet(true))
            }

            MakeQuotationEvent.UpdateBottomSheet -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        quotationUseCases.verifyOtherCharges(
                            bottomSheetChargesState.value.otherChargesLabel,
                            bottomSheetChargesState.value.otherChargesAmount,
                            bottomSheetChargesState.value.otherChargesTax,
                            bottomSheetChargesState.value.otherChargesIsTaxable,
                        )
                        _makeQuotationState.value = makeQuotationState.value.copy(
                            otherChargesLabel = bottomSheetChargesState.value.otherChargesLabel,
                            otherChargesAmount = bottomSheetChargesState.value.otherChargesAmount,
                            otherChargesTax = bottomSheetChargesState.value.otherChargesTax,
                            otherChargesIsTaxable = bottomSheetChargesState.value.otherChargesIsTaxable,
                            showBottomSheet = false
                        )
                        quotationUseCases.updateQuotation(
                            quotation.value.copy(
                                otherChargesLabel = bottomSheetChargesState.value.otherChargesLabel,
                                otherCharges = bottomSheetChargesState.value.otherChargesAmount.toDouble(),
                                otherChargesTax = bottomSheetChargesState.value.otherChargesTax.toDouble(),
                                otherChargesTaxable = bottomSheetChargesState.value.otherChargesIsTaxable
                            )
                        )
                        updateTotal()
                        Log.d(TAG, "onEvent: Bottom Sheet Updated")
                        _eventFlow.tryEmit(UiEvent.ShowBottomSheet(false))
                    } catch (e: Exception) {
                        Log.d(TAG, "onEvent: ${e.message}")
                        _eventFlow.tryEmit(UiEvent.ShowBottomSheetSnackBar(e.message.toString()))
                        // Do not close the bottom sheet here
                    }
                }
            }

            MakeQuotationEvent.ClickedBottomSheetDismiss -> {
                _makeQuotationState.value = makeQuotationState.value.copy(
                    showBottomSheet = false
                )
                _eventFlow.tryEmit(UiEvent.ShowBottomSheet(false))
            }

            MakeQuotationEvent.DeleteOtherCharges -> {
                _makeQuotationState.value = makeQuotationState.value.copy(
                    otherChargesLabel = "",
                    otherChargesAmount = "0",
                    otherChargesTax = "0",
                    otherChargesIsTaxable = false
                )
                viewModelScope.launch(Dispatchers.IO) {
                    quotationUseCases.updateQuotation(
                        quotation.value.copy(
                            otherChargesLabel = "",
                            otherCharges = 0.0,
                            otherChargesTax = 0.0,
                            otherChargesTaxable = false
                        )
                    )
                    updateTotal()
                }
            }

            MakeQuotationEvent.ClickedCustomerPlusButton -> {
                _eventFlow.tryEmit(UiEvent.OpenCustomerList)
            }

            MakeQuotationEvent.DeleteCustomer -> {
                viewModelScope.launch(Dispatchers.IO) {
                    quotationUseCases.updateQuotation(
                        quotation.value.copy(
                            customerId = null
                        )
                    )
                }
            }
        }
    }

    private fun updateTotal() {
        val totalAmountAndGst = quotationUseCases.getTotalAmountAndGst(
            quotationItemList.value,
            makeQuotationState.value
        )
        _totalAmountState.value = totalAmountState.value.copy(
            grandTotal = df.format(totalAmountAndGst.first),
            totalTax = df.format(totalAmountAndGst.second),
        )
    }

    @OptIn(FlowPreview::class)
    private fun getQuotationDetails() {
        viewModelScope.launch(Dispatchers.Default) {

            val quotation = withContext(Dispatchers.IO){ quotationUseCases.getQuotationInProgress() }

            val quotationId = quotation?.id ?: withContext(Dispatchers.IO) { quotationUseCases.addQuotation(Quotation()) }
            _quotationId.longValue = withContext(Dispatchers.Main) { quotationId }

            quotationUseCases.getAllProductsOfQuotation(quotationId)
                .onEach { quotationItem ->
                    _quotationItemList.value = quotationItem
                    updateTotal()
                }
                .debounce(500)
                .flowOn(Dispatchers.IO)
                .launchIn(viewModelScope)

            quotationUseCases.getCustomerOfQuotationId(quotationId)
                .onEach { customer ->
                    _customer.value = customer ?: Customer()
                }
                .flowOn(Dispatchers.IO)
                .launchIn(viewModelScope)

            quotationUseCases.getQuotationWithId(quotationId)
                .onEach { quotation ->
                    _quotation.value = quotation
                    _makeQuotationState.value = makeQuotationState.value.copy(
                        otherChargesLabel = quotation.otherChargesLabel ?: String(),
                        otherChargesAmount = quotation.otherCharges.toString(),
                        otherChargesTax = quotation.otherChargesTax.toString(),
                        otherChargesIsTaxable = quotation.otherChargesTaxable,
                        quotationTime = quotation.quotationTime
                    )
                    _totalAmountState.value = totalAmountState.value.copy(
                        grandTotal = df.format(quotation.totalAmount),
                        totalTax = df.format(quotation.totalGst)
                    )
                    updateTotal()

                }
                .debounce(500)
                .flowOn(Dispatchers.IO)
                .launchIn(viewModelScope)

        }
        df.maximumFractionDigits = 2

    }

    sealed class UiEvent {
        data object OpenCustomerList : UiEvent()
        data object OpenProductList : UiEvent()
        data object ShowDatePicker : UiEvent()
        data class ShowBottomSheet(val show: Boolean) : UiEvent()
        data class ShowSnackbar(val message: String) : UiEvent()
        data object SaveQuotationDetails : UiEvent()
        data class ShowBottomSheetSnackBar(val message: String) : UiEvent()
    }


}