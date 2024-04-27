package com.yogigupta1206.invoicereceiptmaker.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class QuotationItemWithProduct(
    @Embedded val quotationItem: QuotationItem,
    @Relation(
        parentColumn = "productId",
        entityColumn = "id"
    )
    val product: Product
)
