package com.yogigupta1206.invoicereceiptmaker.shared.feature_product.presentation.all_products

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.use_case.ProductUseCases
import com.yogigupta1206.invoicereceiptmaker.shared.utils.OrderBy
import com.yogigupta1206.invoicereceiptmaker.shared.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsUseCases: ProductUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val TAG = "ProductsViewModel"
    }

    private val _productState = mutableStateOf<ProductsState>(ProductsState())
    val productState: State<ProductsState> = _productState

    private var getProductsJob: Job? = null

    var screenLaunchFrom: String = ""
    var quotationId = -1L

    init {
        Log.d(TAG, "initCalled")
        savedStateHandle.get<String>("openFrom")?.let {
            Log.d(TAG, "openFrom: $it")
            screenLaunchFrom = it
        }
        savedStateHandle.get<Long>("id")?.let {
            Log.d(TAG, "quotationId: $it")
            quotationId = it
        }
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