package com.yogigupta1206.invoicereceiptmaker.data.repository

import com.yogigupta1206.invoicereceiptmaker.data.data_source.db.TncDao
import com.yogigupta1206.invoicereceiptmaker.domain.repository.TnCRepository
import kotlinx.coroutines.flow.Flow

class TnCRepositoryImpl(
    private val tncDao: TncDao
): TnCRepository {
    override fun getTnc(): Flow<List<TncDao>> {
        return tncDao.getTnc()
    }

    override suspend fun insertTnc(tnc: TncDao) {
        return tncDao.insertTnc(tnc)
    }

    override suspend fun insertTncList(tncList: List<TncDao>) {
        return tncDao.insertTncList(tncList)
    }

    override suspend fun updateTnc(tnc: TncDao) {
        return tncDao.updateTnc(tnc)
    }

    override suspend fun deleteTnc(tnc: TncDao) {
        return tncDao.deleteTnc(tnc)
    }

    override suspend fun deleteAllTnc() {
        return tncDao.deleteAllTnc()
    }

}