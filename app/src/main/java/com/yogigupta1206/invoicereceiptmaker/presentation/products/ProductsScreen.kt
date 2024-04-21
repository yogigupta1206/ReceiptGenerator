package com.yogigupta1206.invoicereceiptmaker.presentation.products

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yogigupta1206.invoicereceiptmaker.core.Screens
import com.yogigupta1206.invoicereceiptmaker.core.component.TopAppBarTitle
import com.yogigupta1206.invoicereceiptmaker.presentation.products.components.ProductItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    navController: NavController, viewModel: ProductsViewModel = hiltViewModel()
) {

    val state = viewModel.productState.value

    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ), title = {
            TopAppBarTitle(
                title = "Product List"
            )
        }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        })
    }) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            LazyColumn(
                modifier = Modifier
            ) {
                items(state.products.size) { index ->
                    ProductItem(product = state.products[index],
                        modifier = Modifier.padding(vertical = 4.dp),
                        onClick = {
                            navController.navigate(Screens.ProductAddEdit.route + "?productId=${state.products[index].id}")
                        })
                }
            }

            ExtendedFloatingActionButton(modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                text = { Text(text = "Add Product") },
                expanded = true,
                icon = { Icon(Icons.Filled.Add, "Add Product") },
                onClick = {
                    navController.navigate(Screens.ProductAddEdit.route)
                })
        }
    }

}