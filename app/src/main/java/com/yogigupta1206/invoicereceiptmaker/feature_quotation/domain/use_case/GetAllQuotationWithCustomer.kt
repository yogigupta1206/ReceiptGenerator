package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.repository.QuotationRepository

class GetAllQuotationWithCustomer(
    private val quotationRepository: QuotationRepository
) {
    operator fun invoke() = quotationRepository.getAllQuotationsWithCustomer()

}