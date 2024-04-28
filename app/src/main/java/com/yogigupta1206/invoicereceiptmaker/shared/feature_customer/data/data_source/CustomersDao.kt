package com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomersDao {

    @Query("SELECT * FROM customer")
    fun getAllCustomers(): Flow<List<Customer>>

    @Query("SELECT * FROM customer WHERE id = :id")
    suspend fun getCustomerById(id: Long): Customer?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: Customer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCustomers(customers: List<Customer>)

    @Update
    suspend fun updateCustomer(customer: Customer)

    @Delete
    suspend fun deleteCustomer(customer: Customer)

    @Query("DELETE FROM customer WHERE id = :id")
    suspend fun deleteCustomerById(id: Long)

    @Query("DELETE FROM customer")
    suspend fun deleteAllCustomers()
}