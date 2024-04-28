package com.yogigupta1206.invoicereceiptmaker.shared.data_source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.data.data_source.QuotationDao
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationTerms
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.data.data_source.CustomersDao
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.data.data_source.ProductsDao
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.model.Product
import com.yogigupta1206.invoicereceiptmaker.shared.feature_tnc.data.data_source.TncDao
import com.yogigupta1206.invoicereceiptmaker.shared.feature_tnc.domain.model.TnC

@Database(
    entities = [
        Product::class,
        Customer::class,
        TnC::class,
        Quotation::class,
        QuotationTerms::class,
        QuotationItem::class],
    version = 1
)
abstract class AppDbDataSource : RoomDatabase() {

    abstract fun productsDao(): ProductsDao
    abstract fun customersDao(): CustomersDao
    abstract fun tnCDao(): TncDao
    abstract fun quotationDao(): QuotationDao

    companion object {
        const val DATABASE_NAME = "invoice_receipt_maker_db"
    }

}