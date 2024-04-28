package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.repository.QuotationRepository
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer

class UpdateQuotation(
    private val quotationRepository: QuotationRepository
) {

    suspend operator fun invoke(
        customer: Customer?,
        quotation: Quotation,
        itemList: List<QuotationItem>
    ) {
        if (customer == null) {
            throw IllegalArgumentException("Please add customer")
        }
        if (itemList.isEmpty()) {
            throw IllegalArgumentException("Please add product")
        }
        if (customer.id == null) {
            throw IllegalArgumentException("Customer id not found")
        }
        val updatedQuotation = quotation.copy(
            customerId = customer.id
        )
        quotationRepository.updateQuotation(updatedQuotation, itemList)
    }

}