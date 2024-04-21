package com.yogigupta1206.invoicereceiptmaker.presentation.products

import com.yogigupta1206.invoicereceiptmaker.domain.utils.OrderBy

sealed class ProductsEvent {
    data class Order(val orderBy: OrderBy) : ProductsEvent()
}