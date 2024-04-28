package com.yogigupta1206.invoicereceiptmaker.shared.feature_tnc.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yogigupta1206.invoicereceiptmaker.shared.feature_tnc.domain.model.TnC
import kotlinx.coroutines.flow.Flow


@Dao
interface TncDao {

    @Query("SELECT * FROM tnc")
    fun getAllTnc(): Flow<List<TnC>>

    @Query("SELECT * FROM tnc WHERE id = :id")
    suspend fun getTncById(id: Long): TnC

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTnc(tnc: TnC)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTncList(tncList: List<TnC>)

    @Update
    suspend fun updateTnc(tnc: TnC)

    @Delete
    suspend fun deleteTnc(tnc: TnC)

    @Query("DELETE FROM tnc")
    suspend fun deleteAllTnc()

}