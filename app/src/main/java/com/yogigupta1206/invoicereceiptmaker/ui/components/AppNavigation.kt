package com.yogigupta1206.invoicereceiptmaker.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yogigupta1206.invoicereceiptmaker.feature_homepage.HomePageScreen
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.MakeQuotationScreen
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.quotations.QuotationsScreen
import com.yogigupta1206.invoicereceiptmaker.shared.core.Screens
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.presentation.business.BusinessScreen
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.presentation.all_customers.CustomersScreen
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.presentation.customer_add_edit.CustomerAddEditScreen
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.presentation.all_products.ProductsScreen
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.presentation.product_add_edit.ProductAddEditScreen
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.presentation.product_quantity.ProductQuantityScreen

@Composable
fun AppNavigationGraph() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.HomePage.route,
        modifier = Modifier
    ) {

        composable(Screens.HomePage.route) {
            HomePageScreen(navController = navController)
        }

        composable(Screens.Business.route) {
            BusinessScreen(navController = navController)
        }
        composable(Screens.MakeQuotation.route) {
            MakeQuotationScreen(navController = navController)
            //call our composable screens here
        }
        composable(Screens.Items.route) {
            //call our composable screens here
        }
        composable(
            route = Screens.Products.route + "?openFrom={openFrom}&id={id}",
            arguments = listOf(
                navArgument("openFrom") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) {
            ProductsScreen(navController = navController)
        }

        composable(
            route = Screens.ProductQuantity.route + "?productId={productId}&id={id}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.LongType
                    defaultValue = -1L
                },
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )) {
            ProductQuantityScreen(navController = navController)
        }

        composable(
            route = Screens.Customers.route + "?openFrom={openFrom}&id={id}",
            arguments = listOf(
                navArgument("openFrom") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) {
            //call our composable screens here
            CustomersScreen(navController = navController)
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

        composable(Screens.Quotations.route) {
            QuotationsScreen(navController = navController)
        }


    }
}

