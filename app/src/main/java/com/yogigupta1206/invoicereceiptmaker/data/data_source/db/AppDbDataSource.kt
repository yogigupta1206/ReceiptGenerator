package com.yogigupta1206.invoicereceiptmaker.data.data_source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yogigupta1206.invoicereceiptmaker.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.domain.model.Product
import com.yogigupta1206.invoicereceiptmaker.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationTerms
import com.yogigupta1206.invoicereceiptmaker.domain.model.TnC

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