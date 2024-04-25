package com.yogigupta1206.invoicereceiptmaker.presentation.make_quotation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationItemWithProduct

@Composable
fun QuotationItemSection(
    modifier: Modifier = Modifier,
    items: List<QuotationItemWithProduct>,
    onDeleteClicked: () -> Unit,
    onItemClicked: () -> Unit
) {

    if (items.isNotEmpty()) {
        LazyColumn(modifier = modifier) {
            items.forEach { item ->
                item {
                    QuotationItemCardView(
                        item = item,
                        onDeleteClicked = onDeleteClicked,
                        onItemClicked = onItemClicked
                    )
                }
            }
        }

    }
}