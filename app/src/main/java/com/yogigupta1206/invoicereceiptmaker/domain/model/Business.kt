package com.yogigupta1206.invoicereceiptmaker.domain.model

data class Business (
    val businessName: String? = "Business Name",
    val contactName: String? = "Contact Name",
    val email: String? = "Email",
    val phone: String? = "Phone Number",
    val address1: String? = "Address 1",
    val address2: String? = "Address 2",
    val address3: String? = "Address 3",
    val otherInfo: String? = "Other Info",
    val gstPanVanLabel: String? = "GSTIN",
    val gstPanVanNumber: String? = "GSTIN Number",
    val businessCategory: String? = "Business Category",
    val paymentDetails: String? = "Payment Details",
)

class InvalidBusinessException(message: String): Exception(message)
