package com.yogigupta1206.invoicereceiptmaker.shared.feature_product.presentation.product_quantity

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.DiscountType

sealed class ProductQuantityEvent {
    data class EnteredQuantity(val quantity: String) : ProductQuantityEvent()
    data class EnteredPrice(val price: String) : ProductQuantityEvent()
    data class EnteredGstPercentage(val gstPercentage: String) : ProductQuantityEvent()
    data class EnteredDiscountType(val discountType: DiscountType) : ProductQuantityEvent()
    data class EnteredDiscount(val discount: String) : ProductQuantityEvent()
    data class EnteredDescription(val description: String) : ProductQuantityEvent()
    data object DismissDiscountTypeDialog : ProductQuantityEvent()
    data object ShowDiscountTypeDialog : ProductQuantityEvent()
    data object SaveQuantity : ProductQuantityEvent()
}