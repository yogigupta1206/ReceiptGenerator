package com.yogigupta1206.invoicereceiptmaker.shared.feature_tnc.domain.repository

import com.yogigupta1206.invoicereceiptmaker.shared.feature_tnc.domain.model.TnC
import kotlinx.coroutines.flow.Flow

interface TnCRepository {

    fun getAllTnc(): Flow<List<TnC>>

    suspend fun getTncById(id: Long): TnC

    suspend fun insertTnc(tnc: TnC)

    suspend fun insertTncList(tncList: List<TnC>)

    suspend fun updateTnc(tnc: TnC)

    suspend fun deleteTnc(tnc: TnC)

    suspend fun deleteAllTnc()
}