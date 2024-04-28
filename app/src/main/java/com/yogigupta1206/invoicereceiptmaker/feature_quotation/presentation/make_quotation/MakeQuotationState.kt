package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItemWithProduct

data class MakeQuotationState(
    val quotationItemList: List<QuotationItemWithProduct> = emptyList(),
    val otherChargesLabel: String = String(),
    val otherChargesAmount: String = "0",
    val otherChargesIsTaxable: Boolean = false,
    val otherChargesTax: String = "0",
    val quotationTime: Long = System.currentTimeMillis(),
    val showBottomSheet: Boolean = false
)

data class OtherChargesState(
    val otherChargesLabel: String = String(),
    val otherChargesAmount: String = "0",
    val otherChargesIsTaxable: Boolean = false,
    val otherChargesTax: String = "0"
)