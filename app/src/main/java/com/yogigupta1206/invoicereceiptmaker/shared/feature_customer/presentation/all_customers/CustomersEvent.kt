package com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.presentation.all_customers

import com.yogigupta1206.invoicereceiptmaker.shared.utils.OrderBy

sealed class CustomersEvent {
    data class Order(val orderBy: OrderBy) : CustomersEvent()
    data class SelectedIdForQuotation(val id: Long?) : CustomersEvent()
}


