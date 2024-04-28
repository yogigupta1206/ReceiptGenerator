package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.quotations

import com.yogigupta1206.invoicereceiptmaker.shared.utils.OrderBy

sealed class QuotationsEvent {
    data class Order(val orderBy: OrderBy) : QuotationsEvent()

}