package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case

import android.content.Context
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItemWithProduct
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.repository.QuotationRepository
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.MakeQuotationState
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.TotalAmountState
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.repository.BusinessRepository
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.shared.utils.pdf.PdfUtils

class GenerateQuotation(
    private val quotationRepository: QuotationRepository,
    private val businessRepository: BusinessRepository,
    private val context: Context) {

    @Throws(IllegalArgumentException::class)
    suspend operator fun invoke(
        customer: Customer?,
        quotation: Quotation,
        itemList: List<QuotationItemWithProduct>,
        quotationState: MakeQuotationState,
        totalAmountState: TotalAmountState
    ) {
        if (customer?.id == null)
            throw IllegalArgumentException("Please add customer")

        if (itemList.isEmpty())
            throw IllegalArgumentException("Please add product")

        var filePath: String = PdfUtils.generatePdfReceipt(
            context = context,
            business = businessRepository.getBusinessDetails(),
            customer = customer,
            quotationItems = itemList,
            quotation = quotation
        )

        val updatedQuotation = quotation.copy(
            otherChargesLabel = quotationState.otherChargesLabel,
            otherCharges = quotationState.otherChargesAmount.toDouble(),
            otherChargesTaxable = quotationState.otherChargesIsTaxable,
            otherChargesTax = quotationState.otherChargesTax.toDouble(),
            totalAmount = totalAmountState.grandTotal.toDouble(),
            totalGst = totalAmountState.totalTax.toDouble(),
            quotationPath = filePath,
            quotationComplete = true
        )

        quotationRepository.updateQuotation(updatedQuotation)
    }
}
