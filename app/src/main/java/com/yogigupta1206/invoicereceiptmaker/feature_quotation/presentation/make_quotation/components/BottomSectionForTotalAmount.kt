package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.yogigupta1206.invoicereceiptmaker.R
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation.BottomSectionTotalAmountState

@Composable
fun BottomSectionForTotalAmount(
    modifier: Modifier = Modifier,
    state: BottomSectionTotalAmountState,
    onGenerateClick: () -> Unit,
) {

    Box(
        modifier = modifier
            .padding(8.dp)
            .height(64.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(64.dp))
            .background(color = MaterialTheme.colorScheme.onPrimaryContainer)
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.total_gst),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    letterSpacing = TextUnit(0.1f, TextUnitType.Sp)
                )
                Text(
                    text = "${stringResource(R.string.Rs)}${state.totalTax}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    letterSpacing = TextUnit(-1f, TextUnitType.Sp),
                    maxLines = 1,
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f),

                ) {
                Text(
                    text = stringResource(R.string.total_amount),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    letterSpacing = TextUnit(0.1f, TextUnitType.Sp)
                )
                Text(
                    text = "${stringResource(R.string.Rs)}${state.grandTotal}",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    letterSpacing = TextUnit(-1f, TextUnitType.Sp),
                    maxLines = 1,
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(64.dp))
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .clickable {
                        onGenerateClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.generate),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

    }

}


@Composable
@Preview
fun BottomSectionForTotalAmountPreview() {
    Surface(modifier = Modifier) {
        BottomSectionForTotalAmount(
            state = BottomSectionTotalAmountState()
        ) {}
    }

}