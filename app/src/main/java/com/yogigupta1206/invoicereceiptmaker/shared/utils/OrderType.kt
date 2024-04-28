package com.yogigupta1206.invoicereceiptmaker.shared.utils

sealed class OrderType {
    data object Ascending : OrderType()
    data object Descending : OrderType()
}