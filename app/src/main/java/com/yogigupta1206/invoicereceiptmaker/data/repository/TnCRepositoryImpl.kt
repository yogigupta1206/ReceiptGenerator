package com.yogigupta1206.invoicereceiptmaker.data.repository

import com.yogigupta1206.invoicereceiptmaker.data.data_source.db.TncDao
import com.yogigupta1206.invoicereceiptmaker.domain.model.TnC
import com.yogigupta1206.invoicereceiptmaker.domain.repository.TnCRepository
import kotlinx.coroutines.flow.Flow

class TnCRepositoryImpl(
    private val tncDao: TncDao
): TnCRepository {
    override fun getAllTnc(): Flow<List<TnC>> {
        return tncDao.getAllTnc()
    }

    override suspend fun getTncById(id: Int): TnC {
        return tncDao.getTncById(id)
    }

    override suspend fun insertTnc(tnc: TnC) {
        return tncDao.insertTnc(tnc)
    }

    override suspend fun insertTncList(tncList: List<TnC>) {
        return tncDao.insertTncList(tncList)
    }

    override suspend fun updateTnc(tnc: TnC) {
        return tncDao.updateTnc(tnc)
    }

    override suspend fun deleteTnc(tnc: TnC) {
        return tncDao.deleteTnc(tnc)
    }

    override suspend fun deleteAllTnc() {
        return tncDao.deleteAllTnc()
    }

}