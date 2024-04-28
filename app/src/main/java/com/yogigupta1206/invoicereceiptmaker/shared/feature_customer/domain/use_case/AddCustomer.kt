package com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.InvalidCustomerException
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.repository.CustomerRepository
import com.yogigupta1206.invoicereceiptmaker.shared.utils.UserDetailsValidator

class AddCustomer(
    private val customerRepository: CustomerRepository
) {
    @Throws(InvalidCustomerException::class)
    suspend operator fun invoke(customer: Customer){
        if(customer.name.isNullOrBlank()) {
            throw InvalidCustomerException("Customer name can't be empty")
        }
        if(!customer.email.isNullOrBlank() && UserDetailsValidator.isValidEmail(customer.email).not()) {
            throw InvalidCustomerException("Invalid email address")
        }
        customerRepository.insertCustomer(customer)
    }

}