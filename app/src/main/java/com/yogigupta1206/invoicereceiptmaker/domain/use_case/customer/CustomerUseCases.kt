package com.yogigupta1206.invoicereceiptmaker.domain.use_case.customer

data class CustomerUseCases(
    val getCustomers: GetCustomers,
    val getCustomer: GetCustomer,
    val deleteCustomer: DeleteCustomer,
    val addCustomer: AddCustomer
)
