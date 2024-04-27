package com.yogigupta1206.invoicereceiptmaker.domain.use_case.quotation

import com.yogigupta1206.invoicereceiptmaker.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.domain.repository.QuotationRepository

class AddQuotation(
    private val quotationRepository: QuotationRepository
) {

    suspend operator fun invoke(
        quotation: Quotation,
    ): Long {
        return quotationRepository.addQuotation(quotation)
    }

}