package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model

import androidx.room.Embedded
import androidx.room.Relation
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.model.Product

data class QuotationItemWithProduct(
    @Embedded val quotationItem: QuotationItem,
    @Relation(
        parentColumn = "productId",
        entityColumn = "id"
    )
    val product: Product
)
