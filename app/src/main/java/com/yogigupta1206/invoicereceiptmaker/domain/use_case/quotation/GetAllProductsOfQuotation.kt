package com.yogigupta1206.invoicereceiptmaker.domain.use_case.quotation

import com.yogigupta1206.invoicereceiptmaker.domain.repository.QuotationRepository

class GetAllProductsOfQuotation(
    private val repository: QuotationRepository
) {

    operator fun invoke(quotationId: Long) =
        repository.getQuotationAndQuotationItemsById(quotationId)


}