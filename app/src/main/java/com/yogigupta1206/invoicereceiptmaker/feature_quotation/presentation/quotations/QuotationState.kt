package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.quotations

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationWithCustomer
import com.yogigupta1206.invoicereceiptmaker.shared.utils.OrderBy
import com.yogigupta1206.invoicereceiptmaker.shared.utils.OrderType

data class QuotationState(
    val quotationWithCustomersList: List<QuotationWithCustomer> = emptyList(),
    val order: OrderBy = OrderBy.Date(OrderType.Descending)
)
