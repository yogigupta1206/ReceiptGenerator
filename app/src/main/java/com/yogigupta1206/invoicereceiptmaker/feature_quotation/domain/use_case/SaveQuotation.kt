package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.repository.QuotationRepository
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer

class SaveQuotation(
    private val quotationRepository: QuotationRepository
) {

    @Throws(IllegalArgumentException::class)
    suspend operator fun invoke(
        customer: Customer?,
        quotation: Quotation,
        itemList: List<QuotationItem>
    ) {
        if (customer?.id == null)
            throw IllegalArgumentException("Please add customer")

        if (itemList.isEmpty())
            throw IllegalArgumentException("Please add product")

        val updatedQuotation = quotation.copy(
            customerId = customer.id
        )

        quotationRepository.saveQuotation(updatedQuotation, itemList)
    }
}
