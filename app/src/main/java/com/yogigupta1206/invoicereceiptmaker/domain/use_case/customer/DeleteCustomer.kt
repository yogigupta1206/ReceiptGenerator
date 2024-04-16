package com.yogigupta1206.invoicereceiptmaker.domain.use_case.customer

import com.yogigupta1206.invoicereceiptmaker.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.domain.repository.CustomerRepository

class DeleteCustomer(
    private val customerRepository: CustomerRepository
) {
    suspend operator fun invoke(customer: Customer) {
        customerRepository.deleteCustomer(customer)
    }
}