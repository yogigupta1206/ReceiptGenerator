package com.yogigupta1206.invoicereceiptmaker

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.data.data_source.QuotationDao
import com.yogigupta1206.invoicereceiptmaker.shared.data_source.db.AppDbDataSource
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.data.data_source.CustomersDao
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuotationDaoTest {

    private lateinit var quotationDao: QuotationDao
    private lateinit var customersDao: CustomersDao
    private lateinit var db: AppDbDataSource

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDbDataSource::class.java).build()
        quotationDao = db.quotationDao()
        customersDao = db.customersDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

}