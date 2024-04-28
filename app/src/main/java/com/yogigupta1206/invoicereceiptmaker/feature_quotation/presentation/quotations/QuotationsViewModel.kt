package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.quotations

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.QuotationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class QuotationsViewModel @Inject constructor(
    private val quotationUseCases: QuotationUseCases
) : ViewModel() {

    companion object {
        private const val TAG = "QuotationsViewModel"
    }

    private val _quotationState = mutableStateOf(QuotationState())
    val quotationState: State<QuotationState> = _quotationState

    private var getQuotationsJob: Job? = null


    init {
        //getQuotations(OrderBy.Date(OrderType.Descending))
    }

    fun onEvent(event: QuotationsEvent) {
        when (event) {
            is QuotationsEvent.Order -> {
                //getQuotations(event.orderBy)
            }
        }
    }


    /*private fun getQuotations(orderBy: OrderBy) {
        getQuotationsJob?.cancel()
        getQuotationsJob = quotationUseCases.getQuotationWithCustomer().onEach { quotations ->
            _quotationState.value = quotationState.value.copy(
                quotationWithCustomersList = quotations, order = orderBy
            )
        }.launchIn(viewModelScope)
    }*/

}