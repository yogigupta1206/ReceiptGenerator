package com.yogigupta1206.invoicereceiptmaker.presentation.quotations

import com.yogigupta1206.invoicereceiptmaker.domain.utils.OrderBy

sealed class QuotationsEvent {
    data class Order(val orderBy: OrderBy) : QuotationsEvent()

}