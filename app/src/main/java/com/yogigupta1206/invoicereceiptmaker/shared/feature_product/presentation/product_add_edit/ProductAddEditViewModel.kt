package com.yogigupta1206.invoicereceiptmaker.shared.feature_product.presentation.product_add_edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.model.Product
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.use_case.ProductUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductAddEditViewModel @Inject constructor(
    private val productUseCase: ProductUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val TAG = "ProductAddEditViewModel"
    }

    private val _productName = mutableStateOf<String>(String())
    val productName: State<String> = _productName

    private val _productPrice = mutableStateOf(String())
    val productPrice: State<String> = _productPrice

    private val _productUnit = mutableStateOf<String>(String())
    val productUnit: State<String> = _productUnit

    private val _productGstPercentage = mutableStateOf<String>(String())
    val productGstPercentage: State<String> = _productGstPercentage

    private val _productDescription = mutableStateOf<String>(String())
    val productDescription: State<String> = _productDescription

    private val _productHsnCode = mutableStateOf<String>(String())
    val productHsnCode: State<String> = _productHsnCode

    var productId: Long? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        savedStateHandle.get<Long>("productId")?.let { productId ->
            if (productId != -1L) {
                this.productId = productId
                viewModelScope.launch {
                    productUseCase.getProduct(productId)?.also { product ->
                        _productName.value = product.name ?: ""
                        _productPrice.value = product.price.toString()
                        _productUnit.value = product.unit ?: ""
                        _productGstPercentage.value = product.gstPercentage.toString()
                        _productDescription.value = product.description ?: ""
                        _productHsnCode.value = product.hsnCode ?: ""
                    }
                }
            }
        }
    }

    fun onEvent(event: ProductAddEditEvent) {
        when (event) {
            is ProductAddEditEvent.EnteredName -> {
                _productName.value = event.name
            }

            is ProductAddEditEvent.EnteredPrice -> {
                _productPrice.value = event.price
            }

            is ProductAddEditEvent.EnteredUnit -> {
                _productUnit.value = event.unit
            }

            is ProductAddEditEvent.EnteredGstPercentage -> {
                _productGstPercentage.value = event.gstPercentage
            }

            is ProductAddEditEvent.EnteredDescription -> {
                _productDescription.value = event.description
            }

            is ProductAddEditEvent.EnteredHsnCode -> {
                _productHsnCode.value = event.hsnCode
            }

            is ProductAddEditEvent.SaveProduct -> {
                viewModelScope.launch {
                    try {
                        val product = Product(
                            id = productId,
                            name = productName.value,
                            price = if (productPrice.value.isBlank()) 0.0 else productPrice.value.toDouble(),
                            unit = productUnit.value,
                            gstPercentage = if (productGstPercentage.value.isBlank()) 0.0 else productGstPercentage.value.toDouble(),
                            description = productDescription.value,
                            hsnCode = productHsnCode.value
                        )
                        productUseCase.addProduct(product)
                        _eventFlow.emit(UiEvent.SaveProductDetails)
                    } catch (e: Exception) {
                        _eventFlow.emit(UiEvent.ShowSnackbar("Invalid: ${e.message}"))
                    }
                }
            }

            is ProductAddEditEvent.DeleteProduct -> {
                viewModelScope.launch {
                    productId?.let { productId ->
                        productUseCase.deleteProduct(productId)
                    }
                    _eventFlow.emit(UiEvent.SaveProductDetails)
                }
            }

        }

    }


    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data object SaveProductDetails : UiEvent()
    }
}