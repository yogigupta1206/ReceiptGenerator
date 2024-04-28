package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.repository.QuotationRepository

class AddQuotation(
    private val quotationRepository: QuotationRepository
) {

    suspend operator fun invoke(
        quotation: Quotation,
    ): Long {
        return quotationRepository.addQuotation(quotation)
    }

}