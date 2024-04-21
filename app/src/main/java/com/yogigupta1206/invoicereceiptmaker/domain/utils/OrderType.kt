package com.yogigupta1206.invoicereceiptmaker.domain.utils

sealed class OrderType {
    data object Ascending : OrderType()
    data object Descending : OrderType()
}