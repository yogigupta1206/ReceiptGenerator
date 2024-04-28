package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.repository.QuotationRepository

class GetQuotationWithId(
    val repository: QuotationRepository
) {
    operator fun invoke(quotationId: Long) = repository.getQuotationById(quotationId)
}