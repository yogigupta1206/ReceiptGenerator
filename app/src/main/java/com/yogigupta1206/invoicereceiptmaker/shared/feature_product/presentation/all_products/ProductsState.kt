package com.yogigupta1206.invoicereceiptmaker.shared.feature_product.presentation.all_products

import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.model.Product
import com.yogigupta1206.invoicereceiptmaker.shared.utils.OrderBy
import com.yogigupta1206.invoicereceiptmaker.shared.utils.OrderType

data class ProductsState(
    val products: List<Product> = emptyList(),
    val order: OrderBy = OrderBy.Date(OrderType.Descending)
)