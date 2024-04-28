package com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.presentation.all_customers

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.QuotationUseCases
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.use_case.CustomerUseCases
import com.yogigupta1206.invoicereceiptmaker.shared.utils.OrderBy
import com.yogigupta1206.invoicereceiptmaker.shared.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomersViewModel @Inject constructor(
    private val customersUseCases: CustomerUseCases,
    private val quotationUseCases: QuotationUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val TAG = "CustomersViewModel"
    }

    private val _customerState = mutableStateOf(CustomersState())
    val customerState: State<CustomersState> = _customerState

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    var screenLaunchFrom: String = ""
    var quotationId = -1L
    var quotation: Quotation? = null

    private var getCustomersJob: Job? = null

    init {
        Log.d(TAG, "initCalled")
        savedStateHandle.get<String>("openFrom")?.let {
            Log.d(TAG, "openFrom: $it")
            screenLaunchFrom = it
        }
        savedStateHandle.get<Long>("id")?.let {
            Log.d(TAG, "quotationId: $it")
            quotationId = it
            if (it != -1L) {
                quotationUseCases.getQuotationWithId(it)
                    .onEach { quotation ->
                        this.quotation = quotation
                        Log.d(TAG, "Quotation Invoked: $quotation")
                    }
                    .launchIn(viewModelScope)
            }
        }
        getCustomers(OrderBy.Date(OrderType.Descending))
    }

    fun onEvent(event: CustomersEvent) {
        when (event) {
            is CustomersEvent.Order -> {
                getCustomers(event.orderBy)
            }

            is CustomersEvent.SelectedIdForQuotation -> {
                Log.d(TAG, "SelectedIdForQuotation: ${event.id}")
                viewModelScope.launch {
                    quotation?.let {
                        quotationUseCases.updateQuotation(
                            it.copy(
                                customerId = event.id
                            )
                        )
                    }
                    _uiEvent.emit(UiEvent.NavigationBack)
                }
            }
        }
    }


    private fun getCustomers(orderBy: OrderBy) {
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

    sealed class UiEvent {
        data object NavigationBack : UiEvent()
    }

}