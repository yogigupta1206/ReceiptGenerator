package com.yogigupta1206.invoicereceiptmaker.ui.components.navigation

sealed class Screens(val route: String) {
    data object Home : Screens("home")
    data object Search : Screens("search")
    data object Profile : Screens("profile")
}