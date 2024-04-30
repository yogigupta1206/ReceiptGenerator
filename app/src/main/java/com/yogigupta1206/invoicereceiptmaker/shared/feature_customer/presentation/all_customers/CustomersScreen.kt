package com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.presentation.all_customers

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yogigupta1206.invoicereceiptmaker.shared.core.Screens
import com.yogigupta1206.invoicereceiptmaker.shared.core.component.CustomerCardView
import com.yogigupta1206.invoicereceiptmaker.shared.core.component.TopAppBarTitle
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomersScreen(
    navController: NavController, viewModel: CustomersViewModel = hiltViewModel()
) {
    val TAG = "CustomersScreen"
    val state = viewModel.customerState.value

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                CustomersViewModel.UiEvent.NavigationBack -> {
                    navController.popBackStack()
                }
            }
        }
    }

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

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            LazyColumn(
                modifier = Modifier
            ) {
                items(state.customers.size) { index ->
                    CustomerCardView(customer = state.customers[index],
                        modifier = Modifier.padding(vertical = 4.dp),
                        cardOnClick = {
                            Log.d(TAG, "CustomersScreen: Card Clicked")
                            if (viewModel.screenLaunchFrom == Screens.MakeQuotation.route) {
                                viewModel.onEvent(CustomersEvent.SelectedIdForQuotation(state.customers[index].id))
                            } else {
                                navController.navigate(Screens.CustomerAddEdit.route + "?customerId=${state.customers[index].id}")
                            }
                        },
                        iconOnClick = {
                            Log.d(TAG, "CustomersScreen: Icon Clicked")
                            if (viewModel.screenLaunchFrom == Screens.MakeQuotation.route) {
                                viewModel.onEvent(CustomersEvent.SelectedIdForQuotation(state.customers[index].id))
                            } else {
                                navController.navigate(Screens.CustomerAddEdit.route + "?customerId=${state.customers[index].id}")
                            }
                        })
                }
            }

            ExtendedFloatingActionButton(modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                text = { Text(text = "Add Customer") },
                expanded = true,
                icon = { Icon(Icons.Filled.Add, "Add Customer") },
                onClick = {
                    navController.navigate(Screens.CustomerAddEdit.route)
                })
        }
    }


}