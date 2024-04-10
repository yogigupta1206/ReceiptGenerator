package com.yogigupta1206.invoicereceiptmaker.ui.components.navigation

sealed class Screens(val route: String) {
    data object Business : Screens("business")
    data object Customers : Screens("customers")
    data object Discover : Screens("discover")
    data object Products : Screens("products")
    data object Items : Screens("items")
}