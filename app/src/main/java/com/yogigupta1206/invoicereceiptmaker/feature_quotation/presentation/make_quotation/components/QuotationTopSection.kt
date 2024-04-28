package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yogigupta1206.invoicereceiptmaker.shared.utils.toCurrentDataAsString

@Composable
fun QuotationTopSection(
    modifier: Modifier = Modifier,
    quotationTime: Long = System.currentTimeMillis(),
    quotationId: String? = null,
    onClick: () -> Unit
) {

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.clickable { onClick() },
            ) {
                Text(
                    text = "Quotation Date",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light,
                )

                Text(
                    text = quotationTime.toCurrentDataAsString(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Black
                )
            }

            Column {
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = "Quotation No",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light
                )

                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = quotationId ?: "-",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Black
                )
            }


        }
    }

}