package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model

import androidx.room.Embedded
import androidx.room.Relation
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer

data class QuotationWithCustomer(
    @Embedded val quotation: Quotation? = null,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "id"
    )
    val customer: Customer? = null
)
