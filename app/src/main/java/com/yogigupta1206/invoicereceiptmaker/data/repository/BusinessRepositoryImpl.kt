package com.yogigupta1206.invoicereceiptmaker.data.repository

import com.google.gson.Gson
import com.yogigupta1206.invoicereceiptmaker.data.data_source.prefs.AppPrefDataSource
import com.yogigupta1206.invoicereceiptmaker.domain.model.Business
import com.yogigupta1206.invoicereceiptmaker.domain.repository.BusinessRepository
import javax.inject.Inject

class BusinessRepositoryImpl @Inject constructor(
    private val appPrefDataSource: AppPrefDataSource
) : BusinessRepository {
    companion object {
        private const val BUSINESS_KEY = "business_key"
    }

    override suspend fun getBusinessDetails(): Business {
        val businessJson = appPrefDataSource.getStringFromPref(BUSINESS_KEY)
        return Gson().fromJson(businessJson, Business::class.java)
    }

    override suspend fun addBusinessDetails(business: Business) {
        val businessJson = Gson().toJson(business)
        appPrefDataSource.saveInPref(BUSINESS_KEY, businessJson)
    }

    override suspend fun deleteBusinessDetails(business: Business) {
        appPrefDataSource.removeFromPref(BUSINESS_KEY)
    }

}