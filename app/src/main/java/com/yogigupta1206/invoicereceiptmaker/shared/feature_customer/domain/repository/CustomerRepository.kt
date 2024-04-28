package com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.repository

import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {

    fun getAllCustomers(): Flow<List<Customer>>

    suspend fun getCustomerById(id: Long): Customer?

    suspend fun insertCustomer(customer: Customer)

    suspend fun insertAllCustomers(customers: List<Customer>)

    suspend fun updateCustomer(customer: Customer)

    suspend fun deleteCustomer(customer: Customer)

    suspend fun deleteCustomerById(id: Long)

    suspend fun deleteAllCustomers()

}