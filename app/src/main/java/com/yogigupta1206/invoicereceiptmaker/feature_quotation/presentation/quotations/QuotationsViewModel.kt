package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.quotations

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.QuotationUseCases
import com.yogigupta1206.invoicereceiptmaker.shared.utils.OrderBy
import com.yogigupta1206.invoicereceiptmaker.shared.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
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
        viewModelScope.launch(Dispatchers.IO){
            getQuotations(OrderBy.Date(OrderType.Descending))
        }
    }

    fun onEvent(event: QuotationsEvent) {
        when (event) {
            is QuotationsEvent.Order -> {

            }
        }
    }

    private suspend fun getQuotations(orderBy: OrderBy) {
        getQuotationsJob?.cancel()
        getQuotationsJob = quotationUseCases.getCompletedQuotations().onEach { quotations ->
            _quotationState.value = quotationState.value.copy(
                quotationWithCustomersList = quotations, order = orderBy
            )
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

}