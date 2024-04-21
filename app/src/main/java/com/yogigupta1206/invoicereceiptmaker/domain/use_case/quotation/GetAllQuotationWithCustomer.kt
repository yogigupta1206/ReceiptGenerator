package com.yogigupta1206.invoicereceiptmaker.domain.use_case.quotation

import com.yogigupta1206.invoicereceiptmaker.domain.repository.QuotationRepository

class GetAllQuotationWithCustomer(
    private val quotationRepository: QuotationRepository
) {
    operator fun invoke() = quotationRepository.getAllQuotationsWithCustomer()

}