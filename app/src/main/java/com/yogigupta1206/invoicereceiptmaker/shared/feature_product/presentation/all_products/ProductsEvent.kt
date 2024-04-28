package com.yogigupta1206.invoicereceiptmaker.shared.feature_product.presentation.all_products

import com.yogigupta1206.invoicereceiptmaker.shared.utils.OrderBy

sealed class ProductsEvent {
    data class Order(val orderBy: OrderBy) : ProductsEvent()
}