package com.yogigupta1206.invoicereceiptmaker.presentation.customers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yogigupta1206.invoicereceiptmaker.core.component.TopAppBarTitle
import com.yogigupta1206.invoicereceiptmaker.presentation.customers.components.CustomerItem
import com.yogigupta1206.invoicereceiptmaker.core.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomersScreen(
    navController: NavController, viewModel: CustomersViewModel = hiltViewModel()
) {

    val state = viewModel.customerState.value

    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ), title = {
            TopAppBarTitle(
                title = "Customer List"
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

        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(state.customers.size) { index ->
                CustomerItem(
                    customer = state.customers[index],
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            navController.navigate(Screens.CustomerAddEdit.route + "?customerId=${state.customers[index].id}")
                        }
                )
            }
        }

    }


}