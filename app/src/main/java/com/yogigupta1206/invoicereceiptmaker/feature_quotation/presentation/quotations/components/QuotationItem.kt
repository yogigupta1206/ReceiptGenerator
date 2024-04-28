package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.quotations.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationWithCustomer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotationItem(
    quotationWithCustomer: QuotationWithCustomer,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
//                Column(modifier = Modifier.fillMaxHeight()) {
//                    Text(
//                        text = quotationWithCustomer.customer.name ?: "",
//                        style = MaterialTheme.typography.bodyMedium,
//                        fontWeight = FontWeight.Bold
//                    )
//                    if (!quotationWithCustomer.customer.phone.isNullOrBlank()) {
//                        Text(
//                            text = quotationWithCustomer.customer.phone,
//                            style = MaterialTheme.typography.bodySmall,
//                            fontWeight = FontWeight.Medium
//                        )
//                    }
//                }
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxHeight()
//                ) {
//                    Text(
//                        modifier = Modifier.align(Alignment.End),
//                        text = "Quote-${quotationWithCustomer.quotation.id ?: ""}",
//                        style = MaterialTheme.typography.bodySmall,
//                        fontWeight = FontWeight.Medium
//                    )
//
//                    Text(
//                        modifier = Modifier.align(Alignment.End),
//                        text = quotationWithCustomer.quotation.quotationTime.toCurrentDataAsString(),
//                        style = MaterialTheme.typography.bodySmall,
//                        fontWeight = FontWeight.Medium
//                    )
//
//                    Text(
//                        modifier = Modifier.align(Alignment.End),
//                        text = quotationWithCustomer.quotation.quotationTotal.toString(),
//                        style = MaterialTheme.typography.bodyMedium,
//                        fontWeight = FontWeight.Bold
//                    )
//
//                }
            }
        }
    }

}