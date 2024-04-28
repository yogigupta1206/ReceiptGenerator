package com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val gstNumber: String? = null,
    val address1: String? = null,
    val address2: String? = null,
    val otherInfo: String? = null,
    val shippingAddress: String? = null,
    val updatedAt: Long = System.currentTimeMillis()
)

class InvalidCustomerException(message: String) : Exception(message)
