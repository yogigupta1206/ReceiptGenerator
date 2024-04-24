package com.yogigupta1206.invoicereceiptmaker.presentation.product_add_edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yogigupta1206.invoicereceiptmaker.core.component.TextFieldWithTitle
import com.yogigupta1206.invoicereceiptmaker.core.component.TopAppBarTitle
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductAddEditScreen(
    navController: NavController,
    viewModel: ProductAddEditViewModel = hiltViewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                ProductAddEditViewModel.UiEvent.SaveProductDetails -> {
                    snackbarHostState.showSnackbar(
                        message = "Product Saved",
                        duration = SnackbarDuration.Short
                    )
                    navController.popBackStack()
                }

                is ProductAddEditViewModel.UiEvent.ShowSnackbar -> {
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
                title = if (viewModel.productId != null) "Update Info" else "Add Product"
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
                TextFieldWithTitle(label = "Name",
                    text = viewModel.productName.value,
                    onValueChange = {
                        viewModel.onEvent(ProductAddEditEvent.EnteredName(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(
                    label = "Price",
                    text = viewModel.productPrice.value,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        viewModel.onEvent(ProductAddEditEvent.EnteredPrice(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Unit Of Measurement(SET,KG etc.)",
                    text = viewModel.productUnit.value,
                    onValueChange = {
                        viewModel.onEvent(ProductAddEditEvent.EnteredUnit(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "GST Percentage",
                    text = viewModel.productGstPercentage.value,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        viewModel.onEvent(ProductAddEditEvent.EnteredGstPercentage(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Description",
                    text = viewModel.productDescription.value,
                    singleLine = false,
                    onValueChange = {
                        viewModel.onEvent(ProductAddEditEvent.EnteredDescription(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Other Info",
                    text = viewModel.productHsnCode.value,
                    onValueChange = {
                        viewModel.onEvent(ProductAddEditEvent.EnteredHsnCode(it))
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
                if (viewModel.productId != null) {
                    Button(
                        onClick = {
                            viewModel.onEvent(ProductAddEditEvent.DeleteProduct)
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
                        viewModel.onEvent(ProductAddEditEvent.SaveProduct)
                    },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = if (viewModel.productId != null) "Update" else "Add"
                    )
                }
            }

        }
    }

}