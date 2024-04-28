package com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.repository.CustomerRepository

class DeleteCustomer(
    private val customerRepository: CustomerRepository
) {
    suspend operator fun invoke(customerId: Long) {
        customerRepository.deleteCustomerById(customerId)
    }
}