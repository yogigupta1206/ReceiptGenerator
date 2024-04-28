package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.repository.QuotationRepository

class DeleteQuotationItemById(
    private val quotationRepository: QuotationRepository
) {

    suspend operator fun invoke(id: Long) {
        quotationRepository.deleteQuotationItemById(id)
    }

}