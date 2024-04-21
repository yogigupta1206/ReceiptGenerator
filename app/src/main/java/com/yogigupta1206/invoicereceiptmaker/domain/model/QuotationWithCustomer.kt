package com.yogigupta1206.invoicereceiptmaker.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class QuotationWithCustomer(
    @Embedded val quotation: Quotation,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "id"
    )
    val customer: Customer
)
