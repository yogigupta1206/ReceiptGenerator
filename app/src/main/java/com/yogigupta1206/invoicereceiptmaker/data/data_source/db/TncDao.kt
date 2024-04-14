package com.yogigupta1206.invoicereceiptmaker.data.data_source.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface TncDao {

    @Query("SELECT * FROM TnC")
    fun getTnc(): Flow<List<TncDao>>

    @Insert
    suspend fun insertTnc(tnc: TncDao)

    @Insert
    suspend fun insertTncList(tncList: List<TncDao>)

    @Update
    suspend fun updateTnc(tnc: TncDao)

    @Delete
    suspend fun deleteTnc(tnc: TncDao)

    @Query("DELETE FROM TnC")
    suspend fun deleteAllTnc()

}