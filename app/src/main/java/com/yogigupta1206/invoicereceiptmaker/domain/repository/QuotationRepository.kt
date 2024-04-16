package com.yogigupta1206.invoicereceiptmaker.domain.repository

import com.yogigupta1206.invoicereceiptmaker.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationTerms

interface QuotationRepository {

    suspend fun insertQuotation(item: Quotation)

    suspend fun insertQuotationTerms(terms: List<QuotationTerms>)

    fun insertQuotationItems(items: List<QuotationItem>)

    fun updateQuotation(item: Quotation)

    fun updateQuotationItems(items: List<QuotationItem>)

    fun updateQuotationTerms(terms: List<QuotationTerms>)

    fun deleteQuotation(item: Quotation)

    fun deleteQuotationItems(items: List<QuotationItem>)

    fun deleteQuotationTerms(terms: List<QuotationTerms>)

    suspend fun getQuotationsByCustomerId(customerId: Long): List<Quotation>

    suspend fun deleteQuotationsByCustomerId(customerId: Long)

    suspend fun getQuotationItemsByQuotationId(quotationId: Long): List<QuotationItem>

    suspend fun deleteQuotationItemsByQuotationId(quotationId: Long)

    suspend fun getQuotationTermsByQuotationId(quotationId: Long): List<QuotationTerms>

    suspend fun deleteQuotationTermsByQuotationId(quotationId: Long)

}