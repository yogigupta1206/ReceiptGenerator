package com.yogigupta1206.invoicereceiptmaker.feature_homepage.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yogigupta1206.invoicereceiptmaker.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeatureCardItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    imagevector: ImageVector = ImageVector.vectorResource(R.drawable.baseline_list_alt_24),
    onClick: () -> Unit
) {

    Box(modifier) {
        ElevatedCard(
            onClick = onClick,
            modifier = Modifier
                .padding(8.dp)
                .aspectRatio(1f)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Icon(
                modifier = Modifier
                    .padding(start = 32.dp)
                    .size(32.dp),
                imageVector = imagevector,
                contentDescription = "Make Quotation",
                tint = MaterialTheme.colorScheme.surfaceTint
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                text = title,
                style = MaterialTheme.typography.labelLarge,
                overflow = TextOverflow.Visible,
                softWrap = true,
                lineHeight = 20.sp,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                text = description,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Visible,
                softWrap = true,
                lineHeight = 20.sp,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}