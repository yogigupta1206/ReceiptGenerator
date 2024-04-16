package com.yogigupta1206.invoicereceiptmaker.domain.use_case.customer

import com.yogigupta1206.invoicereceiptmaker.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow

class GetCustomers(
    private val customerRepository: CustomerRepository
) {
    operator fun invoke(): Flow<List<Customer>> {
        return customerRepository.getAllCustomers()
    }
}