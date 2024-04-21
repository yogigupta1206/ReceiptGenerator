package com.yogigupta1206.invoicereceiptmaker.presentation.customers

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.customer.CustomerUseCases
import com.yogigupta1206.invoicereceiptmaker.domain.utils.OrderBy
import com.yogigupta1206.invoicereceiptmaker.domain.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CustomersViewModel @Inject constructor(
    private val customersUseCases: CustomerUseCases
) : ViewModel(){
        companion object {
            private const val TAG = "CustomersViewModel"
        }

    private val _customerState = mutableStateOf(CustomersState())
    val customerState: State<CustomersState> = _customerState

    private var getCustomersJob : Job? = null

    init {
        getCustomers(OrderBy.Date(OrderType.Descending))
    }

    fun onEvent(event: CustomersEvent){
        when(event){
            is CustomersEvent.Order -> {
                getCustomers(event.orderBy)
            }
        }
    }


    private fun getCustomers(orderBy: OrderBy){
        getCustomersJob?.cancel()
        getCustomersJob = customersUseCases.getCustomers()
            .onEach { customers ->
                _customerState.value = customerState.value.copy(
                    customers = customers,
                    order = orderBy
                )
            }
            .launchIn(viewModelScope)
    }



}