package com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.use_case

data class CustomerUseCases(
    val getCustomers: GetCustomers,
    val getCustomer: GetCustomer,
    val deleteCustomer: DeleteCustomer,
    val addCustomer: AddCustomer
)
