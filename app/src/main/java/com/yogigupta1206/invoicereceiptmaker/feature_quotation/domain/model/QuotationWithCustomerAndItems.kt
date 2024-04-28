package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model

import androidx.room.Embedded
import androidx.room.Relation
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer

data class QuotationWithCustomerAndItems(
    @Embedded val quotation: Quotation,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "id"
    )
    val customer: Customer,
    @Relation(
        parentColumn = "id",
        entityColumn = "quotationId"
    )
    val items: List<QuotationItem>
)
