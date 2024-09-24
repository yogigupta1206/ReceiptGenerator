package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.MakeQuotationViewModel.UiEvent.SaveQuotationDetails
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.components.BottomSectionForTotalAmount
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.components.LabelsSection
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.components.OtherChargesSection
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.components.QuotationItemCardView
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.components.QuotationTopSection
import com.yogigupta1206.invoicereceiptmaker.shared.core.Screens
import com.yogigupta1206.invoicereceiptmaker.shared.core.component.CustomerCardView
import com.yogigupta1206.invoicereceiptmaker.shared.core.component.OtherChargesBottomSheet
import com.yogigupta1206.invoicereceiptmaker.shared.core.component.TopAppBarTitle
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeQuotationScreen(
    navController: NavController, viewModel: MakeQuotationViewModel = hiltViewModel()
) {
    val TAG = "MakeQuotationScreen"

    val state = viewModel.makeQuotationState.value
    val otherChargesState = viewModel.bottomSheetChargesState.value
    val snackbarHostState = remember { SnackbarHostState() }
    val bottomSheetSnackbarHostState = remember { SnackbarHostState() }
    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            Log.d(TAG, "Event: $event")
            when (event) {
                SaveQuotationDetails -> {
                    snackbarHostState.showSnackbar(
                        message = "Quotation Saved"
                    )
                    navController.popBackStack()
                }

                MakeQuotationViewModel.UiEvent.ShowDatePicker -> {
                    // TODO : Implement Data Picker Callback
                }

                is MakeQuotationViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is MakeQuotationViewModel.UiEvent.ShowBottomSheet -> {
                    if (event.show) {
                        sheetState.show()
                    } else {
                        sheetState.hide()
                    }
                }

                is MakeQuotationViewModel.UiEvent.ShowBottomSheetSnackBar -> {
                    bottomSheetSnackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                MakeQuotationViewModel.UiEvent.OpenCustomerList -> {
                    navController.navigate(Screens.Customers.route + "?openFrom=${Screens.MakeQuotation.route}&id=${viewModel.quotationId.value}")
                }

                MakeQuotationViewModel.UiEvent.OpenProductList -> {
                    navController.navigate(Screens.Products.route + "?openFrom=${Screens.MakeQuotation.route}&id=${viewModel.quotationId.value}")
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
                title = "Make Quotation"
            )
        }, navigationIcon = {
            IconButton(onClick = { navController?.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        })
    }, snackbarHost = { SnackbarHost(snackbarHostState) }) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {

                item {
                    QuotationTopSection(modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant),
                        quotationTime = state.quotationTime,
                        quotationId = viewModel.quotationId.value.toString(),
                        onClick = {
                            // TODO: Show Date Picker
                        })

                    Spacer(modifier = Modifier.height(16.dp))

                    // CUSTOMER SECTION
                    LabelsSection(modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                        label = "TO",
                        onClick = { viewModel.onEvent(MakeQuotationEvent.ClickedCustomerPlusButton) })
                    CustomerCardView(modifier = Modifier.padding(horizontal = 16.dp),
                        customer = viewModel.customer.value,
                        iconImageVector = Icons.Filled.Delete,
                        iconDescription = "Delete Customer",
                        iconOnClick = { viewModel.onEvent(MakeQuotationEvent.DeleteCustomer) },
                        cardOnClick = {})


                    Spacer(modifier = Modifier.height(24.dp))

                    // PRODUCTS SECTION
                    LabelsSection(modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                        label = "PRODUCTS",
                        onClick = { viewModel.onEvent(MakeQuotationEvent.ClickedProductPlusButton) })
                }

                items(viewModel.quotationItemList.value,
                    key = { item -> item.quotationItem.id!! }) { item ->
                    QuotationItemCardView(modifier = Modifier.padding(
                        horizontal = 16.dp, vertical = 8.dp
                    ),
                        item = item,
                        onDeleteClicked = { viewModel.onEvent(MakeQuotationEvent.DeleteProduct(item.quotationItem.id!!)) },
                        onItemClicked = {})
                }

                item {

                    Spacer(modifier = Modifier.height(16.dp))

                    // OTHER CHARGES SECTION
                    LabelsSection(modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                        label = "OTHER CHARGE",
                        onClick = { viewModel.onEvent(MakeQuotationEvent.ClickedOtherChargesPlusButton) })
                    OtherChargesSection(modifier = Modifier.padding(16.dp),
                        state = state,
                        onDeleteClicked = { viewModel.onEvent(MakeQuotationEvent.DeleteOtherCharges) },
                        onItemClicked = { viewModel.onEvent(MakeQuotationEvent.ClickedOtherChargesPlusButton) })
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }

            BottomSectionForTotalAmount(modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                state = viewModel.totalAmountState.value,
                onGenerateClick = {
                    viewModel.onEvent(MakeQuotationEvent.GenerateQuotation)
                })
        }

        OtherChargesBottomSheet(sheetState = sheetState,
            bottomSheetSnackbarHostState = bottomSheetSnackbarHostState,
            showBottomSheet = state.showBottomSheet,
            otherChargesLabel = otherChargesState.otherChargesLabel,
            otherChargesValue = otherChargesState.otherChargesAmount,
            checkBoxChecked = otherChargesState.otherChargesIsTaxable,
            gstValue = otherChargesState.otherChargesTax,
            onSaveClick = { viewModel.onEvent(MakeQuotationEvent.UpdateBottomSheet) },
            onDismissRequest = { viewModel.onEvent(MakeQuotationEvent.ClickedBottomSheetDismiss) },
            onLabelNameChange = { viewModel.onEvent(MakeQuotationEvent.EnteredOtherChargesLabel(it)) },
            onAmountChange = { viewModel.onEvent(MakeQuotationEvent.EnteredOtherChargesValue(it)) },
            onTaxChange = { viewModel.onEvent(MakeQuotationEvent.EnteredOtherChargesIsTaxable(it)) },
            onTaxAmountChange = { viewModel.onEvent(MakeQuotationEvent.EnteredOtherChargesTax(it)) })

    }
}
