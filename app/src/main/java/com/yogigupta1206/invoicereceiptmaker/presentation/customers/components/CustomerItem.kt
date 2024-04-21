package com.yogigupta1206.invoicereceiptmaker.presentation.customers.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
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
import com.yogigupta1206.invoicereceiptmaker.domain.model.Customer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerItem(
    customer: Customer,
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
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit customer details",
                tint = MaterialTheme.colorScheme.surfaceTint
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(end = 32.dp)
            ) {
                Text(
                    text = customer.name ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (!customer.phone.isNullOrBlank()) Text(
                    text = customer.phone,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }




}