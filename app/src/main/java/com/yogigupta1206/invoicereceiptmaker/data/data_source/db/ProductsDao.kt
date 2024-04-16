package com.yogigupta1206.invoicereceiptmaker.data.data_source.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yogigupta1206.invoicereceiptmaker.domain.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<Product>>

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun getProductById(id: Long): Product?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Product>)

    @Update
    suspend fun update(item: Product)

    @Delete
    suspend fun deleteProduct(item: Product)

    @Query("DELETE FROM product WHERE id = :id")
    suspend fun deleteProductById(id: Long)

    @Query("DELETE FROM product")
    suspend fun deleteAllProducts()

}