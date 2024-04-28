package com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow

class GetCustomers(
    private val customerRepository: CustomerRepository
) {
    operator fun invoke(): Flow<List<Customer>> {
        return customerRepository.getAllCustomers()
    }
}