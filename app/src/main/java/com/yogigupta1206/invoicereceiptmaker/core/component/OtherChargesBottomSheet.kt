package com.yogigupta1206.invoicereceiptmaker.core.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherChargesBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    showBottomSheet: Boolean,
    otherChargesLabel: String,
    otherChargesValue: String,
    checkBoxChecked: Boolean,
    gstValue: String,
    onSaveClick: () -> Unit,
    onDismissRequest: () -> Unit,
    onLabelNameChange: (String) -> Unit,
    onAmountChange: (String) -> Unit,
    onTaxChange: (Boolean) -> Unit,
    onTaxAmountChange: (String) -> Unit
) {
    val scope = rememberCoroutineScope()
    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = modifier, onDismissRequest = onDismissRequest, sheetState = sheetState
        ) {

            Text("Other Charge Info")
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldWithTitle(label = "Other Charge Label",
                text = otherChargesLabel,
                onValueChange = {
                    onLabelNameChange(it)
                })
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldWithTitle(label = "Other Charge Amount",
                text = otherChargesValue,
                onValueChange = {
                    onAmountChange(it)
                })
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Is Taxable?")
                Checkbox(checked = checkBoxChecked, onCheckedChange = {
                    onTaxChange(it)
                })
            }
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedVisibility(
                visible = checkBoxChecked,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                TextFieldWithTitle(label = "GST (IN %)", text = gstValue, onValueChange = {
                    onTaxAmountChange(it)
                })
                Spacer(modifier = Modifier.height(8.dp))
            }
            Button(
                onClick = {
                    onSaveClick()
                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onDismissRequest()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text("Save")
            }

        }
    }

}
