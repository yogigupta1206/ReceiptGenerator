package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.MakeQuotationState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherChargesSection(
    modifier: Modifier = Modifier,
    state: MakeQuotationState,
    onDeleteClicked: () -> Unit,
    onItemClicked: () -> Unit
) {
    if (state.otherChargesLabel.isBlank()) return
    Box(modifier = modifier) {
        ElevatedCard(
            onClick = onItemClicked,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = state.otherChargesLabel,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = state.otherChargesAmount,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Light
                        )
                    }
                    if (state.otherChargesIsTaxable) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "GST (${state.otherChargesTax}%)",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = "${state.otherChargesTax.toDouble() * state.otherChargesAmount.toDouble() / 100.0}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Light
                            )
                        }
                    }
                }
                Icon(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .clickable(onClick = onDeleteClicked)
                        .padding(4.dp),
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete product",
                    tint = MaterialTheme.colorScheme.surfaceTint
                )
            }
        }
    }
}