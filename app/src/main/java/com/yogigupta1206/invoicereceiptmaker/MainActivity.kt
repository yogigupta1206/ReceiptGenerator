package com.yogigupta1206.invoicereceiptmaker

import android.content.Context
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
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.DiscountType
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItemWithProduct
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.model.Business
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.model.Product
import com.yogigupta1206.invoicereceiptmaker.shared.utils.pdf.PdfUtils
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
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
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

fun generatePdf(context: Context){

    PdfUtils.generatePdfReceipt(
        context = context,
        business = Business(),
        customer = Customer(1, "TestCustomer", "test@gmail.com", "1234567890", "GST1234567890", "Address 1", "Address 2", "Other Info", "Shipping Address"),
        quotationItems = listOf(
            QuotationItemWithProduct(
                quotationItem = QuotationItem(1, 1, 1, 5, 16.5, 13.5, DiscountType.PERCENTAGE, 82.5, "", 1.0, 109.1),
                product = Product(1, "TestProduct", 100.0, "unit", 18.0, "ddesc", "hsn", 11)
            ),
            QuotationItemWithProduct(
                quotationItem = QuotationItem(2, 1, 2, 10, 18.5, 13.5, DiscountType.PERCENTAGE, 82.5, "", 1.0, 109.1),
                product = Product(1, "TestProduct", 100.0, "unit", 18.0, "ddesc",  "hsn", 11)
            )
        ),
        quotation = Quotation(),
        docType = "Quotation"
    )
}


@Preview(showBackground = true)
@Composable
fun AppEntryPointPreview() {
    InvoiceReceiptMakerTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            AppEntryPoint()
        }
    }
}

