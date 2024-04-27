package com.yogigupta1206.invoicereceiptmaker.domain.use_case.quotation

data class QuotationUseCases(
    val addQuotation: AddQuotation,
    val getAllQuotationWithCustomer: GetAllQuotationWithCustomer,
    val updateQuotation: UpdateQuotation,
    val getAllProductsOfQuotation: GetAllProductsOfQuotation,
    val saveQuotation: SaveQuotation,
    val verifyOtherCharges: VerifyOtherCharges,
)