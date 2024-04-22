package com.yogigupta1206.invoicereceiptmaker.core

sealed class Screens(val route: String) {
    data object Business : Screens("business")
    data object Customers : Screens("customers")
    data object Discover : Screens("discover")
    data object Products : Screens("products")
    data object Items : Screens("items")
    data object CustomerAddEdit : Screens("customerAddEdit")
    data object ProductAddEdit : Screens("productAddEdit")
    data object Quotations : Screens("quotations")
    data object MakeQuotation : Screens("makeQuotation")
}