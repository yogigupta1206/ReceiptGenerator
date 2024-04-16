package com.yogigupta1206.invoicereceiptmaker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String?,
    val email: String?,
    val phone: String?,
    val gstNumber: String?,
    val address1: String?,
    val address2: String?,
    val otherInfo: String?,
    val shippingAddress: String?
)
