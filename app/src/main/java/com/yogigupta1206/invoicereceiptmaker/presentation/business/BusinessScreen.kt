package com.yogigupta1206.invoicereceiptmaker.presentation.business

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yogigupta1206.invoicereceiptmaker.presentation.business.component.TextFieldWithTitle
import com.yogigupta1206.invoicereceiptmaker.ui.theme.InvoiceReceiptMakerTheme
import kotlinx.coroutines.flow.collectLatest


@Composable
fun BusinessScreen(
    navController: NavController,
    viewModel: BusinessViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                BusinessViewModel.UiEvent.SaveDetails -> {
                    navController.navigate("discover")
                }

                is BusinessViewModel.UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
        state.animateScrollTo(100)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(state)
    ) {
        TextFieldWithTitle(label = "BusinessName", text = viewModel.businessName.value.toString(), onValueChange = {
            viewModel.onEvent(BusinessScreenEvent.EnteredBusinessName(it))
        })
        Spacer(modifier = Modifier.height(16.dp))
        TextFieldWithTitle(label = "Contact Name", text = viewModel.contactName.value.toString() , onValueChange = {
            viewModel.onEvent(BusinessScreenEvent.EnteredContactName(it))
        })
        Spacer(modifier = Modifier.height(16.dp))
        TextFieldWithTitle(label = "Email", text = viewModel.email.value.toString(), onValueChange = {
            viewModel.onEvent(BusinessScreenEvent.EnteredEmail(it))
        })
        Spacer(modifier = Modifier.height(16.dp))
        TextFieldWithTitle(label = "Phone Number", text = viewModel.phone.value.toString(), onValueChange = {
            viewModel.onEvent(BusinessScreenEvent.EnteredPhone(it))
        })
        Spacer(modifier = Modifier.height(16.dp))
        TextFieldWithTitle(label = "Address 1", text = viewModel.address1.value.toString(), onValueChange = {
            viewModel.onEvent(BusinessScreenEvent.EnteredAddress1(it))
        })
        Spacer(modifier = Modifier.height(16.dp))
        TextFieldWithTitle(label = "Address 2", text = viewModel.address2.value.toString(), onValueChange = {
            viewModel.onEvent(BusinessScreenEvent.EnteredAddress2(it))
        })
        Spacer(modifier = Modifier.height(16.dp))
        TextFieldWithTitle(label = "Address 3", text = viewModel.address3.value.toString(), onValueChange = {
            viewModel.onEvent(BusinessScreenEvent.EnteredAddress3(it))
        })
        Spacer(modifier = Modifier.height(16.dp))
        TextFieldWithTitle(label = "Other Info", text = viewModel.otherInfo.value.toString(), onValueChange = {
            viewModel.onEvent(BusinessScreenEvent.EnteredOtherInfo(it))
        })
        Spacer(modifier = Modifier.height(16.dp))
        TextFieldWithTitle(label = "GSTIN", text = viewModel.gstPanVanLabel.value.toString(), onValueChange = {
            viewModel.onEvent(BusinessScreenEvent.EnteredGstPanVanLabel(it))
        })
        Spacer(modifier = Modifier.height(16.dp))
        TextFieldWithTitle(label = "GSTIN Number", text = viewModel.gstPanVanNumber.value.toString(), onValueChange = {
            viewModel.onEvent(BusinessScreenEvent.EnteredGstPanVanNumber(it))
        })
        Spacer(modifier = Modifier.height(16.dp))
        TextFieldWithTitle(label = "Business Category", text = viewModel.businessCategory.value.toString(), onValueChange = {
            viewModel.onEvent(BusinessScreenEvent.EnteredBusinessCategory(it))
        })
        Spacer(modifier = Modifier.height(16.dp))
        TextFieldWithTitle(label = "Payment Details", text = viewModel.paymentDetails.value.toString(), onValueChange = {
            viewModel.onEvent(BusinessScreenEvent.EnteredPaymentDetails(it))
        })
    }
}
