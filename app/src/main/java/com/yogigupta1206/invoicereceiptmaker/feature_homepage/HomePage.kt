package com.yogigupta1206.invoicereceiptmaker.feature_homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yogigupta1206.invoicereceiptmaker.R
import com.yogigupta1206.invoicereceiptmaker.feature_homepage.components.FeatureCardItem
import com.yogigupta1206.invoicereceiptmaker.feature_homepage.components.HomePageTopBarItem
import com.yogigupta1206.invoicereceiptmaker.shared.core.Screens

@Composable
fun HomePageScreen(
    navController: NavController,
    viewModel: HomePageViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 8.dp, start = 16.dp)
                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = "Welcome",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Narendra",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clip(RoundedCornerShape(12.dp)) // adjust the corner size to your liking
                    .background(MaterialTheme.colorScheme.surfaceVariant) // adjust the background color to your liking
                    .clickable {
                        // TODO: Implement the navigation
                    }
            ) {
                Icon(
                    modifier = Modifier
                        .size(38.dp)
                        .padding(6.dp),
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            text = "Manage",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .wrapContentHeight()
        ) {
            HomePageTopBarItem(
                modifier = Modifier
                    .weight(1f),
                icon = Icons.Filled.Home,
                text = "Business",
                onClick = {
                    navController.navigate(Screens.Business.route)
                }
            )

            HomePageTopBarItem(
                modifier = Modifier
                    .weight(1f),
                icon = Icons.Filled.Person,
                text = "Customers",
                onClick = {
                    navController.navigate(Screens.Customers.route)
                }
            )

            HomePageTopBarItem(
                modifier = Modifier
                    .weight(1f),
                icon = Icons.Filled.ShoppingCart,
                text = "Products",
                onClick = {
                    navController.navigate(Screens.Products.route)
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            text = "Quick Actions",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .wrapContentHeight()
        ) {
            FeatureCardItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                title = "Make Quotation",
                imagevector = ImageVector.vectorResource(R.drawable.list_alt_add_24px),
                description = "Create New Quotation",
                onClick = {
                    navController.navigate(Screens.MakeQuotation.route)
                }
            )
            FeatureCardItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                title = "Quotation List",
                description = "Manage All Quotations",
                onClick = {
                    navController.navigate(Screens.Quotations.route)
                }
            )
        }

    }


}