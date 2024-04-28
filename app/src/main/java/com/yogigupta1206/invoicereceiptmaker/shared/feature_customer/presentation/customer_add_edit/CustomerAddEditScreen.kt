package com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.presentation.customer_add_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yogigupta1206.invoicereceiptmaker.shared.core.component.TextFieldWithTitle
import com.yogigupta1206.invoicereceiptmaker.shared.core.component.TopAppBarTitle
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerAddEditScreen(
    navController: NavController,
    viewModel: CustomerAddEditViewModel = hiltViewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                CustomerAddEditViewModel.UiEvent.SaveCustomerDetails -> {
                    snackbarHostState.showSnackbar(
                        message = "Customer Saved",
                        duration = SnackbarDuration.Short
                    )
                    navController.popBackStack()
                }

                is CustomerAddEditViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                    )
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
                title = if (viewModel.customerId != null) "Update Info" else "Add Customer"
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
    }, snackbarHost = { SnackbarHost(snackbarHostState) }) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
                .background(MaterialTheme.colorScheme.background)
        ) {

            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .weight(1f)

            ) {
                Spacer(modifier = Modifier.height(8.dp))
                TextFieldWithTitle(label = "Name",
                    text = viewModel.customerName.value,
                    onValueChange = {
                        viewModel.onEvent(CustomerAddEditEvent.EnteredName(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(
                    label = "Email",
                    text = viewModel.customerEmail.value,
                    onValueChange = {
                        viewModel.onEvent(CustomerAddEditEvent.EnteredEmail(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Phone Number",
                    text = viewModel.customerPhone.value,
                    onValueChange = {
                        viewModel.onEvent(CustomerAddEditEvent.EnteredPhone(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Address 1",
                    text = viewModel.customerAddress1.value,
                    onValueChange = {
                        viewModel.onEvent(CustomerAddEditEvent.EnteredAddress1(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Address 2",
                    text = viewModel.customerAddress2.value,
                    onValueChange = {
                        viewModel.onEvent(CustomerAddEditEvent.EnteredAddress2(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Other Info",
                    text = viewModel.customerOtherInfo.value,
                    onValueChange = {
                        viewModel.onEvent(CustomerAddEditEvent.EnteredOtherInfo(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Shipping Address",
                    text = viewModel.customerShippingAddress.value,
                    onValueChange = {
                        viewModel.onEvent(CustomerAddEditEvent.EnteredShippingAddress(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            ) {
                if(viewModel.customerId != null) {
                    Button(
                        onClick = {
                            viewModel.onEvent(CustomerAddEditEvent.DeleteCustomer)
                        },
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text("Remove")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
                Button(
                    onClick = {
                        viewModel.onEvent(CustomerAddEditEvent.SaveCustomer)
                    },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = if (viewModel.customerId != null) "Update" else "Add"
                    )
                }
            }

        }
    }


}