package com.yogigupta1206.invoicereceiptmaker.feature_homepage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor() : ViewModel() {

    companion object {
        private const val TAG = "HomePageViewModel"
    }

    private val _eventFlow = MutableSharedFlow<UiEvent>(extraBufferCapacity = 1)
    val eventFlow = _eventFlow.asSharedFlow()


    sealed class UiEvent {
        data object OpenBusinessScreen : UiEvent()
        data object OpenCustomerScreen : UiEvent()
        data object OpenProductScreen : UiEvent()
        data object OpenQuotationScreen : UiEvent()
        data object MakeQuotationScreen : UiEvent()
    }

}