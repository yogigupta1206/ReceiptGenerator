package com.yogigupta1206.invoicereceiptmaker.presentation.customers

import com.yogigupta1206.invoicereceiptmaker.domain.utils.OrderBy

sealed class CustomersEvent{
    data class Order(val orderBy: OrderBy) : CustomersEvent()
}


