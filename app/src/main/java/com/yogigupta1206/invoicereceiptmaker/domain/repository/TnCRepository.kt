package com.yogigupta1206.invoicereceiptmaker.domain.repository

import com.yogigupta1206.invoicereceiptmaker.data.data_source.db.TncDao
import kotlinx.coroutines.flow.Flow

interface TnCRepository {

    fun getTnc(): Flow<List<TncDao>>

    suspend fun insertTnc(tnc: TncDao)

    suspend fun insertTncList(tncList: List<TncDao>)

    suspend fun updateTnc(tnc: TncDao)

    suspend fun deleteTnc(tnc: TncDao)

    suspend fun deleteAllTnc()
}