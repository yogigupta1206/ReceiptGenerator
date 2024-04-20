package com.yogigupta1206.invoicereceiptmaker.presentation.business

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yogigupta1206.invoicereceiptmaker.presentation.business.component.TextFieldWithTitle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.format.TextStyle


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun BusinessScreen(
    navController: NavController, viewModel: BusinessViewModel = hiltViewModel()
) {
    val TAG = "BusinessScreen"
    val snackbarHostState = remember { SnackbarHostState() }
    val state = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                BusinessViewModel.UiEvent.SaveDetails -> {
                    snackbarHostState.showSnackbar(
                        message = "Details Saved", duration = SnackbarDuration.Short
                    )
                    navController.navigate("discover")
                }

                is BusinessViewModel.UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
        state.animateScrollTo(100)
    }

    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ), title = {
            Text(
                text= "Update Business Info",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontStyle = MaterialTheme.typography.headlineSmall.fontStyle,
                fontWeight = FontWeight.Bold,
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
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {

            Column(
                modifier = Modifier
                    .verticalScroll(state)
                    .weight(1f)

            ) {
                TextFieldWithTitle(label = "BusinessName",
                    text = viewModel.businessName.value.toString(),
                    onValueChange = {
                        viewModel.onEvent(BusinessScreenEvent.EnteredBusinessName(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Contact Name",
                    text = viewModel.contactName.value.toString(),
                    onValueChange = {
                        viewModel.onEvent(BusinessScreenEvent.EnteredContactName(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(
                    label = "Email",
                    text = viewModel.email.value.toString(),
                    onValueChange = {
                        viewModel.onEvent(BusinessScreenEvent.EnteredEmail(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Phone Number",
                    text = viewModel.phone.value.toString(),
                    onValueChange = {
                        viewModel.onEvent(BusinessScreenEvent.EnteredPhone(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Address 1",
                    text = viewModel.address1.value.toString(),
                    onValueChange = {
                        viewModel.onEvent(BusinessScreenEvent.EnteredAddress1(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Address 2",
                    text = viewModel.address2.value.toString(),
                    onValueChange = {
                        viewModel.onEvent(BusinessScreenEvent.EnteredAddress2(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Address 3",
                    text = viewModel.address3.value.toString(),
                    onValueChange = {
                        viewModel.onEvent(BusinessScreenEvent.EnteredAddress3(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Other Info",
                    text = viewModel.otherInfo.value.toString(),
                    onValueChange = {
                        viewModel.onEvent(BusinessScreenEvent.EnteredOtherInfo(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "GSTIN",
                    text = viewModel.gstPanVanLabel.value.toString(),
                    onValueChange = {
                        viewModel.onEvent(BusinessScreenEvent.EnteredGstPanVanLabel(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "GSTIN Number",
                    text = viewModel.gstPanVanNumber.value.toString(),
                    onValueChange = {
                        viewModel.onEvent(BusinessScreenEvent.EnteredGstPanVanNumber(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Business Category",
                    text = viewModel.businessCategory.value.toString(),
                    onValueChange = {
                        viewModel.onEvent(BusinessScreenEvent.EnteredBusinessCategory(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldWithTitle(label = "Payment Details",
                    text = viewModel.paymentDetails.value.toString(),
                    onValueChange = {
                        viewModel.onEvent(BusinessScreenEvent.EnteredPaymentDetails(it))
                    })
                Spacer(modifier = Modifier.height(16.dp))

            }

            Button(
                onClick = {
                    viewModel.onEvent(BusinessScreenEvent.SaveBusinessDetails)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text("Update")
            }

        }
    }


}
