package com.yogigupta1206.invoicereceiptmaker.shared.feature_product.presentation.product_quantity.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.DiscountType

@Composable
fun DiscountTypeDialog(
    dialogState: State<Boolean>,
    onDismiss: () -> Unit,
    onDiscountTypeSelected: (DiscountType) -> Unit
) {

    if (dialogState.value) {
        Dialog(onDismissRequest = onDismiss) {
            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                items(DiscountType.entries.size) { index ->
                    Text(
                        text = DiscountType.entries[index].name,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onDiscountTypeSelected(DiscountType.entries[index])
                                onDismiss()
                            }
                            .padding(16.dp),
                    )
                    Divider(color = MaterialTheme.colorScheme.onPrimaryContainer, thickness = 1.dp)
                }
            }

        }
    }


}