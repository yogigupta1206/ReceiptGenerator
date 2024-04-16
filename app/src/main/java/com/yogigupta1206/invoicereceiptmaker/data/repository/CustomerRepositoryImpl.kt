package com.yogigupta1206.invoicereceiptmaker.data.repository

import com.yogigupta1206.invoicereceiptmaker.data.data_source.db.CustomersDao
import com.yogigupta1206.invoicereceiptmaker.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow

class CustomerRepositoryImpl(
    private val customersDao: CustomersDao
) : CustomerRepository {
    override fun getAllCustomers(): Flow<List<Customer>> {
        return customersDao.getAllCustomers()
    }

    override suspend fun getCustomerById(id: Long): Customer? {
        return customersDao.getCustomerById(id)
    }

    override suspend fun insertCustomer(customer: Customer) {
        return customersDao.insertCustomer(customer)
    }

    override suspend fun insertAllCustomers(customers: List<Customer>) {
        return customersDao.insertAllCustomers(customers)
    }

    override suspend fun updateCustomer(customer: Customer) {
        return customersDao.updateCustomer(customer)
    }

    override suspend fun deleteCustomer(customer: Customer) {
        return customersDao.deleteCustomer(customer)
    }

    override suspend fun deleteCustomerById(id: Long) {
        return customersDao.deleteCustomerById(id)
    }

    override suspend fun deleteAllCustomers() {
        return customersDao.deleteAllCustomers()
    }


}