package com.yogigupta1206.invoicereceiptmaker.domain.use_case.customer

import com.yogigupta1206.invoicereceiptmaker.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.domain.repository.CustomerRepository

class GetCustomer(
    private val customerRepository: CustomerRepository
) {
    suspend operator fun invoke(customerId: Long): Customer? {
        return customerRepository.getCustomerById(customerId)
    }
}