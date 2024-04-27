package com.yogigupta1206.invoicereceiptmaker.core.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherChargesBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    bottomSheetSnackbarHostState: SnackbarHostState,
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
    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = modifier, onDismissRequest = onDismissRequest, sheetState = sheetState
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = "Other Charge Info",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldWithTitle(
                        label = "Other Charge Label",
                        text = otherChargesLabel,
                        onValueChange = {
                            onLabelNameChange(it)
                        })
                    Spacer(modifier = Modifier.height(8.dp))
                    TextFieldWithTitle(
                        label = "Other Charge Amount", text = otherChargesValue, onValueChange = {
                            onAmountChange(it)
                        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        Modifier
                            .padding(horizontal = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Is Taxable?")
                        Checkbox(modifier = Modifier.size(24.dp),
                            checked = checkBoxChecked,
                            onCheckedChange = {
                                onTaxChange(it)
                            })
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    AnimatedVisibility(
                        visible = checkBoxChecked,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        TextFieldWithTitle(
                            label = "GST (IN %)", text = gstValue, onValueChange = {
                                onTaxAmountChange(it)
                            }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                SnackbarHost(
                    hostState = bottomSheetSnackbarHostState,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .fillMaxWidth()
                )
            }
            Button(
                onClick = {
                    onSaveClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text("Save")
            }
        }
    }

}
