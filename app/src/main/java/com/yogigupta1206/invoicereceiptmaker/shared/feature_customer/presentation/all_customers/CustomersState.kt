package com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.presentation.all_customers

import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.shared.utils.OrderBy
import com.yogigupta1206.invoicereceiptmaker.shared.utils.OrderType

data class CustomersState (
    val customers: List<Customer> = emptyList(),
    val order: OrderBy = OrderBy.Date(OrderType.Descending)
)

