package com.yogigupta1206.invoicereceiptmaker.ui.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.yogigupta1206.invoicereceiptmaker.R
import com.yogigupta1206.invoicereceiptmaker.core.Screens

data class BottomNavigationItem(
    val label : String = "",
    val icon : Any = Icons.Filled.Star,
    val route : String = ""
) {

    //function to get the list of bottomNavigationItems
    @Composable
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Business",
                icon = painterResource(id = R.drawable.store_24),
                route = Screens.Business.route
            ),
            BottomNavigationItem(
                label = "Customer",
                icon = Icons.Filled.AccountBox,
                route = Screens.Customers.route
            ),
            BottomNavigationItem(
                label = "Discover",
                icon = Icons.Filled.Star,
                route = Screens.Discover.route
            ),
            BottomNavigationItem(
                label = "Product",
                icon = Icons.Filled.Email,
                route = Screens.Products.route
            ),
            BottomNavigationItem(
                label = "Items",
                icon = Icons.Filled.List,
                route = Screens.Items.route
            ),
        )
    }
}