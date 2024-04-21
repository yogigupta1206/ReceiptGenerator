package com.yogigupta1206.invoicereceiptmaker.presentation.customers

import com.yogigupta1206.invoicereceiptmaker.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.domain.utils.OrderType
import com.yogigupta1206.invoicereceiptmaker.domain.utils.OrderBy

data class CustomersState (
    val customers: List<Customer> = emptyList(),
    val order: OrderBy = OrderBy.Date(OrderType.Descending)
)

