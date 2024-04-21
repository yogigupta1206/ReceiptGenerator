package com.yogigupta1206.invoicereceiptmaker.presentation.products

import com.yogigupta1206.invoicereceiptmaker.domain.model.Product
import com.yogigupta1206.invoicereceiptmaker.domain.utils.OrderBy
import com.yogigupta1206.invoicereceiptmaker.domain.utils.OrderType

data class ProductsState(
    val products: List<Product> = emptyList(),
    val order: OrderBy = OrderBy.Date(OrderType.Descending)
)