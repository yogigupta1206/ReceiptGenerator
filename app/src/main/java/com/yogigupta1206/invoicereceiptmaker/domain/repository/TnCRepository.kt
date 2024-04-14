package com.yogigupta1206.invoicereceiptmaker.domain.repository

import com.yogigupta1206.invoicereceiptmaker.domain.model.TnC
import kotlinx.coroutines.flow.Flow

interface TnCRepository {

    fun getAllTnc(): Flow<List<TnC>>

    suspend fun getTncById(id: Int): TnC

    suspend fun insertTnc(tnc: TnC)

    suspend fun insertTncList(tncList: List<TnC>)

    suspend fun updateTnc(tnc: TnC)

    suspend fun deleteTnc(tnc: TnC)

    suspend fun deleteAllTnc()
}