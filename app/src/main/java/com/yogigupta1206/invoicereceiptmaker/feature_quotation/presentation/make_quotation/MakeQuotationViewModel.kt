package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItemWithProduct
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.QuotationUseCases
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MakeQuotationViewModel @Inject constructor(
    private val quotationUseCases: QuotationUseCases,
) : ViewModel() {

    companion object {
        private const val TAG = "MakeQuotationViewModel"
    }

    private val _makeQuotationState = mutableStateOf(MakeQuotationState())
    val makeQuotationState: State<MakeQuotationState> = _makeQuotationState

    private val _quotationId = mutableLongStateOf(-1L)
    val quotationId: State<Long> = _quotationId

    private val _otherChargesState = mutableStateOf(OtherChargesState())
    val otherChargesState: State<OtherChargesState> = _otherChargesState

    private val _customer = mutableStateOf(Customer())
    val customer: State<Customer> = _customer

    private val _eventFlow = MutableSharedFlow<UiEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()

    private val _quotationItemList = mutableStateOf(listOf<QuotationItemWithProduct>())
    val quotationItemList: State<List<QuotationItemWithProduct>> = _quotationItemList

    private val _quotation = mutableStateOf(Quotation())
    val quotation: State<Quotation> = _quotation


    init {
        getQuotationDetails()
    }

    fun onEvent(event: MakeQuotationEvent) {
        when (event) {

            is MakeQuotationEvent.ClickedProductPlusButton -> {
                _eventFlow.tryEmit(UiEvent.OpenProductList)
            }

            is MakeQuotationEvent.DeleteProduct -> {
                viewModelScope.launch {
                    quotationUseCases.deleteQuotationItemById(event.quotationItemId)
                }
            }

            is MakeQuotationEvent.EnteredOtherChargesLabel -> {
                _otherChargesState.value = otherChargesState.value.copy(
                    otherChargesLabel = event.label
                )
            }

            is MakeQuotationEvent.EnteredOtherChargesTax -> {
                _otherChargesState.value = otherChargesState.value.copy(
                    otherChargesTax = event.tax
                )
            }

            is MakeQuotationEvent.EnteredOtherChargesIsTaxable -> {
                _otherChargesState.value = otherChargesState.value.copy(
                    otherChargesIsTaxable = event.isTaxable
                )
            }

            is MakeQuotationEvent.EnteredOtherChargesValue -> {
                _otherChargesState.value = otherChargesState.value.copy(
                    otherChargesAmount = event.value
                )
            }

            is MakeQuotationEvent.UpdateQuotationDate -> {
                _makeQuotationState.value = makeQuotationState.value.copy(
                    quotationTime = event.date
                )
            }

            MakeQuotationEvent.GenerateQuotation -> {
            }

            is MakeQuotationEvent.ClickedOtherChargesPlusButton -> {
                _otherChargesState.value = otherChargesState.value.copy(
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
                viewModelScope.launch {
                    try {
                        quotationUseCases.verifyOtherCharges(
                            otherChargesState.value.otherChargesLabel,
                            otherChargesState.value.otherChargesAmount,
                            otherChargesState.value.otherChargesTax,
                            otherChargesState.value.otherChargesIsTaxable,
                        )
                        _makeQuotationState.value = makeQuotationState.value.copy(
                            otherChargesLabel = otherChargesState.value.otherChargesLabel,
                            otherChargesAmount = otherChargesState.value.otherChargesAmount,
                            otherChargesTax = otherChargesState.value.otherChargesTax,
                            otherChargesIsTaxable = otherChargesState.value.otherChargesIsTaxable
                        )
                        _makeQuotationState.value = makeQuotationState.value.copy(
                            showBottomSheet = false
                        )
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
            }

            MakeQuotationEvent.ClickedCustomerPlusButton -> {
                _eventFlow.tryEmit(UiEvent.OpenCustomerList)
            }

            MakeQuotationEvent.DeleteCustomer -> {
                viewModelScope.launch {
                    quotationUseCases.updateQuotation(
                        quotation.value.copy(
                            customerId = null
                        )
                    )
                }
            }
        }
    }

    private fun getQuotationDetails() {
        viewModelScope.launch {
            val quotationId = quotationUseCases.addQuotation(Quotation())
            _quotationId.longValue = quotationId

            quotationUseCases.getAllProductsOfQuotation(quotationId)
                .onEach { quotationItem ->
                    _quotationItemList.value = quotationItem
                }
                .launchIn(viewModelScope)

            quotationUseCases.getCustomerOfQuotationId(quotationId)
                .onEach { customer ->
                    _customer.value = customer ?: Customer()
                }
                .launchIn(viewModelScope)

            quotationUseCases.getQuotationWithId(quotationId)
                .onEach { quotation ->
                    _quotation.value = quotation
                }
                .launchIn(viewModelScope)
        }

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