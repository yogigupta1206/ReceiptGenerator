package com.yogigupta1206.invoicereceiptmaker.presentation.products

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.product.ProductUseCases
import com.yogigupta1206.invoicereceiptmaker.domain.utils.OrderBy
import com.yogigupta1206.invoicereceiptmaker.domain.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsUseCases: ProductUseCases
) : ViewModel() {
    companion object {
        private const val TAG = "ProductsViewModel"
    }

    private val _productState = mutableStateOf<ProductsState>(ProductsState())
    val productState: State<ProductsState> = _productState

    private var getProductsJob: Job? = null

    init {
        getProducts(OrderBy.Date(OrderType.Descending))
    }

    fun onEvent(event: ProductsEvent) {
        when (event) {
            is ProductsEvent.Order -> {
                getProducts(event.orderBy)
            }
        }
    }


    private fun getProducts(orderBy: OrderBy) {
        getProductsJob?.cancel()
        getProductsJob = productsUseCases.getProducts()
            .onEach { products ->
                _productState.value = productState.value.copy(
                    products = products,
                    order = orderBy
                )
            }
            .launchIn(viewModelScope)
    }


}