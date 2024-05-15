package com.yogigupta1206.invoicereceiptmaker.shared.core.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerCardView(
    modifier: Modifier = Modifier,
    customer: Customer? = null,
    iconImageVector: ImageVector = Icons.Default.Edit,
    iconDescription: String = "Edit customer details",
    iconOnClick: () -> Unit = {},
    cardOnClick: () -> Unit = {}
) {
    if (customer == null || customer.name.isNullOrBlank()) return

    Spacer(modifier = Modifier.height(8.dp))
    Box(modifier = modifier) {
        ElevatedCard(
            onClick = cardOnClick,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable { iconOnClick() }
                        .padding(4.dp),
                    imageVector = iconImageVector,
                    contentDescription = iconDescription,
                    tint = MaterialTheme.colorScheme.surfaceTint
                )

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .padding(end = 32.dp)
                ) {
                    Text(
                        text = customer.name,
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

}