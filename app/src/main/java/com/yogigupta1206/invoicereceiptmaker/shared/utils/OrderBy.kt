package com.yogigupta1206.invoicereceiptmaker.shared.utils

sealed class OrderBy(orderType: OrderType) {
    data class Name(val orderType: OrderType) : OrderBy(orderType)
    data class Date(val orderType: OrderType) : OrderBy(orderType)
}

