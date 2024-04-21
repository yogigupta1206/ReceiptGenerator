package com.yogigupta1206.invoicereceiptmaker.domain.repository

import com.yogigupta1206.invoicereceiptmaker.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationWithCustomer
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationWithCustomerAndItems
import kotlinx.coroutines.flow.Flow

interface QuotationRepository {
    suspend fun insertNewQuotation(quotation: Quotation, itemList: List<QuotationItem>)
    suspend fun deleteQuotationById(quotationId: Long)
    suspend fun updateQuotation(quotation: Quotation, itemList: List<QuotationItem>)
    fun getAllQuotationsWithCustomer(): Flow<List<QuotationWithCustomer>>

    suspend fun getQuotationAndQuotationItemsById(quotationId: Long): QuotationWithCustomerAndItems

}