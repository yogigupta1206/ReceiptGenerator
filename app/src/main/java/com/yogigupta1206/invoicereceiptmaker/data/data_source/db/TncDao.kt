package com.yogigupta1206.invoicereceiptmaker.data.data_source.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.yogigupta1206.invoicereceiptmaker.domain.model.TnC
import kotlinx.coroutines.flow.Flow


@Dao
interface TncDao {

    @Query("SELECT * FROM tnc")
    fun getAllTnc(): Flow<List<TnC>>

    @Query("SELECT * FROM tnc WHERE id = :id")
    suspend fun getTncById(id: Long): TnC

    @Insert
    suspend fun insertTnc(tnc: TnC)

    @Insert
    suspend fun insertTncList(tncList: List<TnC>)

    @Update
    suspend fun updateTnc(tnc: TnC)

    @Delete
    suspend fun deleteTnc(tnc: TnC)

    @Query("DELETE FROM tnc")
    suspend fun deleteAllTnc()

}