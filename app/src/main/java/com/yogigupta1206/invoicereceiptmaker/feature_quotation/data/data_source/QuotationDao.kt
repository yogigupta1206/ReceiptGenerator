package com.yogigupta1206.invoicereceiptmaker.feature_quotation.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItemWithProduct
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationTerms
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationWithCustomer
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationWithCustomerAndItems
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import kotlinx.coroutines.flow.Flow

@Dao
interface QuotationDao {

    @Transaction
    @Query("SELECT * FROM Quotation WHERE id = :quotationId")
    fun getQuotationWithCustomerDetails(quotationId: Long): Flow<QuotationWithCustomer>

    @Transaction
    @Query("SELECT * FROM Quotation WHERE id = :quotationId")
    fun getQuotationsWithCustomerAndItemsById(quotationId: Long): Flow<QuotationWithCustomerAndItems>

    @Query("SELECT * FROM Customer WHERE id IN (SELECT customerId FROM Quotation WHERE id = :quotationId)")
    fun getCustomerOfQuotationId(quotationId: Long): Flow<Customer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotationAndGetId(item: Quotation): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotationTerms(terms: List<QuotationTerms>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotationItem(item: QuotationItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotationItems(items: List<QuotationItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotation(quotation: Quotation)

    @Update
    suspend fun updateQuotation(item: Quotation)

    @Update
    suspend fun updateQuotationItems(items: List<QuotationItem>)

    @Update
    suspend fun updateQuotationTerms(terms: List<QuotationTerms>)

    @Delete
    suspend fun deleteQuotation(item: Quotation)

    @Query("DELETE FROM Quotation WHERE id = :id")
    suspend fun deleteQuotationById(id: Long)

    @Delete
    suspend fun deleteQuotationItems(items: List<QuotationItem>)

    @Query("SELECT * FROM Quotation WHERE id = :quotationId")
    fun getQuotationById(quotationId: Long): Flow<Quotation>

    @Delete
    suspend fun deleteQuotationTerms(terms: List<QuotationTerms>)

    @Query("SELECT * FROM Quotation WHERE customerId = :customerId")
    suspend fun getQuotationsByCustomerId(customerId: Long): List<Quotation>

    @Query("DELETE FROM Quotation WHERE customerId = :customerId")
    suspend fun deleteQuotationsByCustomerId(customerId: Long)

    @Query("SELECT * FROM QuotationItem WHERE quotationId = :quotationId")
    fun getQuotationItemsByQuotationId(quotationId: Long): Flow<List<QuotationItem>>

    @Transaction
    @Query("SELECT * FROM QuotationItem WHERE quotationId = :quotationId")
    fun getQuotationItemWithProductByQuotationId(quotationId: Long): Flow<List<QuotationItemWithProduct>>

    @Query("DELETE FROM QuotationItem WHERE quotationId = :quotationId")
    suspend fun deleteQuotationItemsByQuotationId(quotationId: Long)

    @Query("SELECT * FROM QuotationTerms WHERE quotationId = :quotationId")
    suspend fun getQuotationTermsByQuotationId(quotationId: Long): List<QuotationTerms>

    @Query("DELETE FROM QuotationTerms WHERE quotationId = :quotationId")
    suspend fun deleteQuotationTermsByQuotationId(quotationId: Long)

    @Query("SELECT * FROM QuotationItem WHERE quotationId = :quotationId")
    fun getAllProductsOfQuotation(quotationId: Long): Flow<List<QuotationItem>>

}