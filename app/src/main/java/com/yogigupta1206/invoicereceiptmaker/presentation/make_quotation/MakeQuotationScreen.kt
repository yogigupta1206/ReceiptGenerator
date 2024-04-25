package com.yogigupta1206.invoicereceiptmaker.presentation.make_quotation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yogigupta1206.invoicereceiptmaker.core.component.CustomerCardView
import com.yogigupta1206.invoicereceiptmaker.core.component.OtherChargesBottomSheet
import com.yogigupta1206.invoicereceiptmaker.core.component.TopAppBarTitle
import com.yogigupta1206.invoicereceiptmaker.presentation.make_quotation.MakeQuotationViewModel.UiEvent.SaveQuotationDetails
import com.yogigupta1206.invoicereceiptmaker.presentation.make_quotation.components.LabelsSection
import com.yogigupta1206.invoicereceiptmaker.presentation.make_quotation.components.QuotationItemSection
import com.yogigupta1206.invoicereceiptmaker.presentation.make_quotation.components.QuotationTopSection
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeQuotationScreen(
    navController: NavController? = null,
    viewModel: MakeQuotationViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()
    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                SaveQuotationDetails -> {
                    snackbarHostState.showSnackbar(
                        message = "Quotation Saved"
                    )
                    navController?.popBackStack()
                }

                MakeQuotationViewModel.UiEvent.ShowDatePicker -> TODO()
                is MakeQuotationViewModel.UiEvent.ShowSnackbar -> TODO()
                is MakeQuotationViewModel.UiEvent.ShowBottomSheet -> {
                    if (event.show) {
                        sheetState.show()
                    } else {
                        sheetState.hide()
                    }

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
            IconButton(onClick = {
                navController?.popBackStack()
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
                .verticalScroll(scrollState),
        ) {

            QuotationTopSection(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                quotationTime = viewModel.quotationTime.value,
                onClick = {
                    // TODO: Show Date Picker
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            LabelsSection(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                label = "TO",
                onClick = {

                }
            )
            CustomerCardView(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                iconImageVector = Icons.Filled.Delete,
                iconDescription = "Delete Customer",
                iconOnClick = {

                },
                cardOnClick = {

                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            LabelsSection(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                label = "PRODUCTS",
                onClick = {

                }
            )
            QuotationItemSection(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                items = viewModel.quotationItemList.value,
                onDeleteClicked = {

                },
                onItemClicked = {

                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            LabelsSection(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                label = "OTHER CHARGE",
                onClick = {
                    Log.d("MakeQuotationScreen", "Other Charge Clicked")
                    viewModel.onEvent(MakeQuotationEvent.ShowBottomSheet(true))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        OtherChargesBottomSheet(
            modifier = Modifier,
            sheetState = sheetState,
            showBottomSheet = viewModel.showBottomSheet.value,
            otherChargesLabel = viewModel.otherChargesLabel.value,
            otherChargesValue = viewModel.otherChargesValue.value,
            checkBoxChecked = viewModel.otherChargesTaxable.value,
            gstValue = viewModel.otherChargesTax.value,
            onSaveClick = {

            },
            onDismissRequest = {
                viewModel.onEvent(MakeQuotationEvent.ShowBottomSheet(false))
            },
            onLabelNameChange = {

            },
            onAmountChange = {

            },
            onTaxChange = {

            },
            onTaxAmountChange = {

            }
        )

    }
}
