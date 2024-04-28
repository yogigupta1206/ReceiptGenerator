package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.repository.QuotationRepository

class GetAllProductsOfQuotation(
    private val repository: QuotationRepository
) {

    operator fun invoke(quotationId: Long) =
        repository.getQuotationAndQuotationItemsById(quotationId)


}