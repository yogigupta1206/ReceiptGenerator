package com.yogigupta1206.invoicereceiptmaker.domain.repository

import android.content.Context
import com.yogigupta1206.invoicereceiptmaker.domain.model.Business

interface BusinessRepository {
    suspend fun getBusinessDetails(context: Context): Business
    suspend fun updateBusinessDetails(context: Context , business: Business)
    suspend fun deleteBusinessDetails(context: Context, business: Business)
}