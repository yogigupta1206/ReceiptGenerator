package com.yogigupta1206.invoicereceiptmaker.ui.components.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.MakeQuotationScreen
import com.yogigupta1206.invoicereceiptmaker.shared.core.Screens
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.presentation.business.BusinessScreen
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.presentation.all_customers.CustomersScreen
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.presentation.customer_add_edit.CustomerAddEditScreen
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.presentation.all_products.ProductsScreen
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.presentation.product_add_edit.ProductAddEditScreen

@Composable
fun BottomNavigationBar() {
    var navigationSelectedItem by remember {
        mutableIntStateOf(2)
    }
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                BottomNavigationItem().bottomNavigationItems()
                    .forEachIndexed { index, navigationItem ->
                        NavigationBarItem(
                            selected = index == navigationSelectedItem,
                            label = {
                                Text(navigationItem.label)
                            },
                            icon = {
                                if (navigationItem.icon is ImageVector) {
                                    Icon(
                                        navigationItem.icon,
                                        contentDescription = navigationItem.label
                                    )
                                } else {
                                    Icon(
                                        painter = navigationItem.icon as Painter,
                                        contentDescription = navigationItem.label
                                    )
                                }

                            },
                            onClick = {
                                navigationSelectedItem = index
                                navController.navigate(navigationItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.MakeQuotation.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(Screens.Business.route) {
                BusinessScreen(navController = navController)
            }
            composable(
                route = Screens.Customers.route,
                arguments = listOf(navArgument("openFrom") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                    navArgument("quotationId") {
                        type = NavType.LongType
                        defaultValue = -1
                    }
                )
            ) {
                //call our composable screens here
                CustomersScreen(navController = navController)
            }
            composable(Screens.MakeQuotation.route) {
                MakeQuotationScreen(navController = navController)
                //call our composable screens here
            }
            composable(Screens.Items.route) {
                //call our composable screens here
            }
            composable(Screens.Products.route) {
                ProductsScreen(navController = navController)
            }

            composable(
                route = Screens.CustomerAddEdit.route + "?customerId={customerId}",
                arguments = listOf(navArgument(
                    "customerId"
                ) {
                    type = NavType.LongType
                    defaultValue = -1
                })
            ) {
                CustomerAddEditScreen(navController = navController)
            }

            composable(
                route = Screens.ProductAddEdit.route + "?productId={productId}",
                arguments = listOf(navArgument(
                    "productId"
                ) {
                    type = NavType.LongType
                    defaultValue = -1
                })
            ) {
                ProductAddEditScreen(navController = navController)
            }


        }
    }
}