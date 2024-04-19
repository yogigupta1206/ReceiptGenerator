package com.yogigupta1206.invoicereceiptmaker.domain.repository

import com.yogigupta1206.invoicereceiptmaker.domain.model.Business

interface BusinessRepository {
    suspend fun getBusinessDetails(): Business
    suspend fun addBusinessDetails(business: Business)
    suspend fun deleteBusinessDetails(business: Business)
}