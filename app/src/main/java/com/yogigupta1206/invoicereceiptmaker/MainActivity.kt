package com.yogigupta1206.invoicereceiptmaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.yogigupta1206.invoicereceiptmaker.ui.components.AppNavigationGraph
import com.yogigupta1206.invoicereceiptmaker.ui.theme.InvoiceReceiptMakerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            InvoiceReceiptMakerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().background(Color.White)
                ) {
                    AppEntryPoint()
                }
            }
        }
    }
}

@Composable
fun AppEntryPoint() {
    AppNavigationGraph()
}

@Preview(showBackground = true)
@Composable
fun AppEntryPointPreview() {
    InvoiceReceiptMakerTheme {
        Surface(
            modifier = Modifier.fillMaxSize().background(Color.White)
        ) {
            AppEntryPoint()
        }
    }
}

