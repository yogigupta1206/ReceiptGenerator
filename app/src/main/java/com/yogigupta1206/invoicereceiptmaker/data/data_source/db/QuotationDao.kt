package com.yogigupta1206.invoicereceiptmaker.data.data_source.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.yogigupta1206.invoicereceiptmaker.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationTerms

@Dao
interface QuotationDao {

    @Insert
    suspend fun insertQuotation(item: Quotation)

    @Insert
    suspend fun insertQuotationTerms(terms: List<QuotationTerms>)

    @Insert
    fun insertQuotationItems(items: List<QuotationItem>)

    @Update
    fun updateQuotation(item: Quotation)

    @Update
    fun updateQuotationItems(items: List<QuotationItem>)

    @Update
    fun updateQuotationTerms(terms: List<QuotationTerms>)

    @Delete
    fun deleteQuotation(item: Quotation)

    @Delete
    fun deleteQuotationItems(items: List<QuotationItem>)

    @Delete
    fun deleteQuotationTerms(terms: List<QuotationTerms>)

    @Query("SELECT * FROM Quotation WHERE customerId = :customerId")
    suspend fun getQuotationsByCustomerId(customerId: Long): List<Quotation>

    @Query("DELETE FROM Quotation WHERE customerId = :customerId")
    suspend fun deleteQuotationsByCustomerId(customerId: Long)

    @Query("SELECT * FROM QuotationItem WHERE quotationId = :quotationId")
    suspend fun getQuotationItemsByQuotationId(quotationId: Long): List<QuotationItem>

    @Query("DELETE FROM QuotationItem WHERE quotationId = :quotationId")
    suspend fun deleteQuotationItemsByQuotationId(quotationId: Long)

    @Query("SELECT * FROM QuotationTerms WHERE quotationId = :quotationId")
    suspend fun getQuotationTermsByQuotationId(quotationId: Long): List<QuotationTerms>

    @Query("DELETE FROM QuotationTerms WHERE quotationId = :quotationId")
    suspend fun deleteQuotationTermsByQuotationId(quotationId: Long)

}