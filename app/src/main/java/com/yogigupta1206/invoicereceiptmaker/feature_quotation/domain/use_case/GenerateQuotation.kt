package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.repository.QuotationRepository
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.MakeQuotationState
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.TotalAmountState
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer

class GenerateQuotation(
    private val quotationRepository: QuotationRepository
) {

    @Throws(IllegalArgumentException::class)
    suspend operator fun invoke(
        customer: Customer?,
        quotation: Quotation,
        itemList: List<QuotationItem>,
        quotationState: MakeQuotationState,
        totalAmountState: TotalAmountState
    ) {
        if (customer?.id == null)
            throw IllegalArgumentException("Please add customer")

        if (itemList.isEmpty())
            throw IllegalArgumentException("Please add product")

        val updatedQuotation = quotation.copy(
            otherChargesLabel = quotationState.otherChargesLabel,
            otherCharges = quotationState.otherChargesAmount.toDouble(),
            otherChargesTaxable = quotationState.otherChargesIsTaxable,
            otherChargesTax = quotationState.otherChargesTax.toDouble(),
            totalAmount = totalAmountState.grandTotal.toDouble(),
            totalGst = totalAmountState.totalTax.toDouble(),
            quotationComplete = true
        )

        quotationRepository.updateQuotation(updatedQuotation)
    }
}
