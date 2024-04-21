package com.yogigupta1206.invoicereceiptmaker.domain.model

import androidx.room.Embedded
import androidx.room.Relation

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
