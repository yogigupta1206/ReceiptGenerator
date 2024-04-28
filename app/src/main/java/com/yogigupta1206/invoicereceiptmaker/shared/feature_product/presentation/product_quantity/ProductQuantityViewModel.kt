package com.yogigupta1206.invoicereceiptmaker.shared.feature_product.presentation.product_quantity

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.DiscountType
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.QuotationUseCases
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.model.Product
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.use_case.ProductUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductQuantityViewModel @Inject constructor(
    private val productUseCases: ProductUseCases,
    private val quotationUseCases: QuotationUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val TAG = "ProductQuantityViewModel"
    }

    private val _product: MutableState<Product?> = mutableStateOf(Product())
    val product: State<Product?> = _product

    private val _eventFlow = MutableSharedFlow<UiEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()

    private val _amount = mutableStateOf(String())
    val amount: State<String> = _amount

    private val _quantity = mutableStateOf(String())
    val quantity: State<String> = _quantity

    private val _discount = mutableStateOf(String())
    val discount: State<String> = _discount

    private val _gstPercentage = mutableStateOf(String())
    val gstPercentage: State<String> = _gstPercentage

    private val _discountType = mutableStateOf(DiscountType.PERCENTAGE)
    val discountType: State<DiscountType> = _discountType

    private val _discountTypeDialogState = mutableStateOf(false)
    val discountTypeDialogState: State<Boolean> = _discountTypeDialogState

    private val _description = mutableStateOf("")
    val description: State<String> = _description

    var productId: Long = -1
    var quotationId: Long = -1


    init {
        Log.d(TAG, "initCalled")
        savedStateHandle.get<Long>("productId")?.let {
            Log.d(TAG, "productId: $it")
            productId = it
            getProductDetails(productId)
        }
        savedStateHandle.get<Long>("id")?.let {
            Log.d(TAG, "quotationId: $it")
            quotationId = it
        }
    }

    fun onEvent(event: ProductQuantityEvent) {
        when (event) {
            is ProductQuantityEvent.EnteredDescription -> {
                _description.value = event.description
            }

            is ProductQuantityEvent.EnteredDiscount -> {
                _discount.value = event.discount
            }

            is ProductQuantityEvent.EnteredDiscountType -> {
                _discountType.value = event.discountType
            }

            is ProductQuantityEvent.EnteredGstPercentage -> {
                _gstPercentage.value = event.gstPercentage
            }

            is ProductQuantityEvent.EnteredPrice -> {
                _amount.value = event.price
            }

            is ProductQuantityEvent.EnteredQuantity -> {
                _quantity.value = event.quantity
            }

            ProductQuantityEvent.SaveQuantity -> {
                viewModelScope.launch {
                    try {
                        quotationUseCases.addQuotationProduct(
                            QuotationItem(
                                quotationId = quotationId,
                                productId = productId,
                                quantity = quantity.value.toInt(),
                                price = amount.value.toDouble(),
                                gst = gstPercentage.value.toDouble(),
                                discountType = discountType.value,
                                discount = discount.value.toDouble(),
                                description = description.value
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveProductDetails)
                    } catch (e: Exception) {
                        _eventFlow.emit(UiEvent.ShowSnackbar("Error: ${e.message}"))
                    }
                }
            }

            ProductQuantityEvent.DismissDiscountTypeDialog -> {
                _discountTypeDialogState.value = false
            }

            ProductQuantityEvent.ShowDiscountTypeDialog -> {
                _discountTypeDialogState.value = true
            }
        }
    }

    private fun getProductDetails(productId: Long) {
        viewModelScope.launch {
            productUseCases.getProduct(productId)
                .let {
                    _product.value = it
                }
        }
    }


    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data object SaveProductDetails : UiEvent()
    }
}