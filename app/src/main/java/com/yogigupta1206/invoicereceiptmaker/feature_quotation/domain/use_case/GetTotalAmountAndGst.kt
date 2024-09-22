package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItemWithProduct
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.MakeQuotationState

class GetTotalAmountAndGst {
    operator fun invoke(
        quotationItemList: List<QuotationItemWithProduct>,
        makeQuotationState: MakeQuotationState
    ): Pair<Double, Double> {

        var totalAmount = quotationItemList.sumOf { it.quotationItem.totalAmount }
        var gstAmount = quotationItemList.sumOf { it.quotationItem.totalGst }

        val chargesGst =
            if (makeQuotationState.otherChargesIsTaxable) makeQuotationState.otherChargesTax.toDouble() * makeQuotationState.otherChargesAmount.toDouble() / 100 else 0.0
        val totalCharges = makeQuotationState.otherChargesAmount.toDouble() + chargesGst

        totalAmount += totalCharges
        gstAmount += chargesGst

        return Pair(totalAmount, gstAmount)
    }
}