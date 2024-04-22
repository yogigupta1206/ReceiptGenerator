package com.yogigupta1206.invoicereceiptmaker.presentation.quotations

import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationWithCustomer
import com.yogigupta1206.invoicereceiptmaker.domain.utils.OrderBy
import com.yogigupta1206.invoicereceiptmaker.domain.utils.OrderType

data class QuotationState(
    val quotationWithCustomersList: List<QuotationWithCustomer> = emptyList(),
    val order: OrderBy = OrderBy.Date(OrderType.Descending)
)
