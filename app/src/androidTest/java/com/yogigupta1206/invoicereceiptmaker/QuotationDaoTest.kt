package com.yogigupta1206.invoicereceiptmaker

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.data.data_source.QuotationDao
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.shared.data_source.db.AppDbDataSource
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.data.data_source.CustomersDao
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
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

    @Test
    fun testGetAllQuotationsWithCustomerDetails() = runBlocking {
        // Insert a test customer
        val customer =
            Customer(1, "Test Customer", "", "", "", "Address_TestCustomer_1", "", "", "")
        customersDao.insertCustomer(customer)

        // Insert a test customer
        val customer1 =
            Customer(2, "Test Customer 2", "", "", "", "Address_TestCustomer_2", "", "", "")
        customersDao.insertCustomer(customer1)

        // Insert a test quotation
        val quotation = Quotation(id = 1, customerId = 2, quotationTotal = 90.5)
        quotationDao.insertQuotation(quotation)

        // Insert a test quotation
        val quotation1 = Quotation(id = 3, customerId = 1, quotationTotal = 120.5)
        quotationDao.insertQuotation(quotation1)

        // Insert a test quotation
        val quotation2 = Quotation(id = 2, customerId = 2, quotationTotal = 190.5)
        quotationDao.insertQuotation(quotation2)

        // Run the query

    }
}