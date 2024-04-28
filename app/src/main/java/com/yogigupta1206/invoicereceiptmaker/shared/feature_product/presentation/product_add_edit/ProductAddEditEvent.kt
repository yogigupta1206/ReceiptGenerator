package com.yogigupta1206.invoicereceiptmaker.shared.feature_product.presentation.product_add_edit

sealed class ProductAddEditEvent {
    data class EnteredName(val name: String) : ProductAddEditEvent()
    data class EnteredPrice(val price: String) : ProductAddEditEvent()
    data class EnteredUnit(val unit: String) : ProductAddEditEvent()
    data class EnteredGstPercentage(val gstPercentage: String) : ProductAddEditEvent()
    data class EnteredDescription(val description: String) : ProductAddEditEvent()
    data class EnteredHsnCode(val hsnCode: String) : ProductAddEditEvent()
    data object SaveProduct : ProductAddEditEvent()
    data object DeleteProduct : ProductAddEditEvent()
}