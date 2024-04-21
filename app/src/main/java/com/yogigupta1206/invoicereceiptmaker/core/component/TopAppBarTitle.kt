package com.yogigupta1206.invoicereceiptmaker.core.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TopAppBarTitle(
    title: String
) {
    Text(
        text= title,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontStyle = MaterialTheme.typography.headlineSmall.fontStyle,
        fontWeight = FontWeight.Bold,
    )
}