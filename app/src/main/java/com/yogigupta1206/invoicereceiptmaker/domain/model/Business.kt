package com.yogigupta1206.invoicereceiptmaker.domain.model

data class Business (
    val businessName: String? = null,
    val contactName: String? ,
    val email: String?,
    val phone: String?,
    val address1: String?,
    val address2: String?,
    val address3: String?,
    val otherInfo: String?,
    val gstPanVanLabel: String?,
    val gstPanVanNumber: String?,
    val businessCategory: String?,
    val paymentDetails: String?,
)

class InvalidBusinessException(message: String): Exception(message)
