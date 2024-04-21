package com.yogigupta1206.invoicereceiptmaker.presentation.customer_add_edit

sealed class CustomerAddEditEvent {
    data class EnteredName(val name: String): CustomerAddEditEvent()
    data class EnteredEmail(val email: String): CustomerAddEditEvent()
    data class EnteredPhone(val phone: String): CustomerAddEditEvent()
    data class EnteredGstNumber(val gstNumber: String): CustomerAddEditEvent()
    data class EnteredAddress1(val address1: String): CustomerAddEditEvent()
    data class EnteredAddress2(val address2: String): CustomerAddEditEvent()
    data class EnteredOtherInfo(val otherInfo: String): CustomerAddEditEvent()
    data class EnteredShippingAddress(val shippingAddress: String): CustomerAddEditEvent()
    data object SaveCustomer: CustomerAddEditEvent()
    data object DeleteCustomer: CustomerAddEditEvent()
}