package com.yogigupta1206.invoicereceiptmaker.domain.use_case.quotation

import com.yogigupta1206.invoicereceiptmaker.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.domain.repository.QuotationRepository

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
        quotation.customerId = customer.id
        quotationRepository.updateQuotation(quotation, itemList)
    }

}