package com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.repository.CustomerRepository

class GetCustomer(
    private val customerRepository: CustomerRepository
) {
    suspend operator fun invoke(customerId: Long): Customer? {
        return customerRepository.getCustomerById(customerId)
    }
}