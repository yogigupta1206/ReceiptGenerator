package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.MakeQuotationState

@Composable
fun QuotationItemSection(
    modifier: Modifier = Modifier,
    state: MakeQuotationState,
    onDeleteClicked: () -> Unit,
    onItemClicked: () -> Unit
) {

    if (state.quotationItemList.isNotEmpty()) {
        LazyColumn(modifier = modifier) {
            state.quotationItemList.forEach { item ->
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