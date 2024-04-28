package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case

data class QuotationUseCases(
    val addQuotation: AddQuotation,
    val getCustomerOfQuotationId: GetCustomerOfQuotationId,
    val updateQuotation: UpdateQuotation,
    val getAllProductsOfQuotation: GetAllProductsOfQuotation,
    val saveQuotation: SaveQuotation,
    val verifyOtherCharges: VerifyOtherCharges,
    val getQuotationWithId: GetQuotationWithId,
)