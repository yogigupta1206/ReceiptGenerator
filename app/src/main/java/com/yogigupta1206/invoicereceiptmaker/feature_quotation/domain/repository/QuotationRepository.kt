package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.repository

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItemWithProduct
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationWithCustomer
import kotlinx.coroutines.flow.Flow

interface QuotationRepository {
    suspend fun addQuotation(quotation: Quotation): Long
    fun getQuotationById(quotationId: Long): Flow<Quotation>
    suspend fun saveQuotation(quotation: Quotation, itemList: List<QuotationItem>)
    suspend fun deleteQuotationById(quotationId: Long)
    suspend fun updateQuotation(quotation: Quotation, itemList: List<QuotationItem>)
    fun getAllQuotationsWithCustomer(): Flow<List<QuotationWithCustomer>>
    fun getQuotationAndQuotationItemsById(quotationId: Long): Flow<List<QuotationItemWithProduct>>
    fun getAllProductsOfQuotation(quotationId: Long): Flow<List<QuotationItem>>

}