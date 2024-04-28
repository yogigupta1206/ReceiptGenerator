package com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.repository

import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.model.Business

interface BusinessRepository {
    suspend fun getBusinessDetails(): Business
    suspend fun addBusinessDetails(business: Business)
    suspend fun deleteBusinessDetails(business: Business)
}