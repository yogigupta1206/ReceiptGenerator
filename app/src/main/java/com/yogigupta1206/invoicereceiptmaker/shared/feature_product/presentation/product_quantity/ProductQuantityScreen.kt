package com.yogigupta1206.invoicereceiptmaker.shared.feature_product.presentation.product_quantity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.DiscountType
import com.yogigupta1206.invoicereceiptmaker.shared.core.Screens
import com.yogigupta1206.invoicereceiptmaker.shared.core.component.TextFieldWithTitle
import com.yogigupta1206.invoicereceiptmaker.shared.core.component.TopAppBarTitle
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.presentation.product_quantity.components.DiscountTypeDialog
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductQuantityScreen(
    navController: NavController,
    viewModel: ProductQuantityViewModel = hiltViewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                ProductQuantityViewModel.UiEvent.SaveProductDetails -> {
                    navController.popBackStack(
                        route = Screens.MakeQuotation.route,
                        inclusive = false
                    )
                }

                is ProductQuantityViewModel.UiEvent.ShowSnackbar -> {
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
                title = "Add Quotation Product"
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
        ) {

            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .weight(1f)

            ) {
                Spacer(modifier = Modifier.height(8.dp))
                TextFieldWithTitle(label = "Product",
                    enabled = false,
                    text = viewModel.product.value?.name.toString(),
                    onValueChange = {})
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(
                    label = "Quantity",
                    text = viewModel.quantity.value,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        viewModel.onEvent(ProductQuantityEvent.EnteredQuantity(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Price",
                    text = viewModel.amount.value,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        viewModel.onEvent(ProductQuantityEvent.EnteredPrice(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "GST Percentage",
                    text = viewModel.gstPercentage.value,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        viewModel.onEvent(ProductQuantityEvent.EnteredGstPercentage(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))


                // TODO : Discount Type Screen Dialog
                TextFieldWithTitle(label = "Discount type",
                    text = viewModel.discountType.value.name,
                    singleLine = false,
                    onValueChange = {},
                    enabled = false,
                    onClick = {
                        viewModel.onEvent(ProductQuantityEvent.ShowDiscountTypeDialog)
                    }
                )
                DiscountTypeDialog(
                    dialogState = viewModel.discountTypeDialogState,
                    onDismiss = {
                        viewModel.onEvent(ProductQuantityEvent.DismissDiscountTypeDialog)
                    },
                    onDiscountTypeSelected = {
                        viewModel.onEvent(ProductQuantityEvent.EnteredDiscountType(it))
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))


                TextFieldWithTitle(label = if (viewModel.discountType.value == DiscountType.PERCENTAGE) "Discount (In %)" else "Discount Amount",
                    text = viewModel.discount.value,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        viewModel.onEvent(ProductQuantityEvent.EnteredDiscount(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))

                TextFieldWithTitle(label = "Description",
                    text = viewModel.description.value,
                    onValueChange = {
                        viewModel.onEvent(ProductQuantityEvent.EnteredDescription(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
            }


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                onClick = {
                    viewModel.onEvent(ProductQuantityEvent.SaveQuantity)
                }) {
                Text("Add Product")
            }

        }
    }


}