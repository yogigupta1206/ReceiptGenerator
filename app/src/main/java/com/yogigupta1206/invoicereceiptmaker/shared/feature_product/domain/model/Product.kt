package com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product (
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String?,
    val price: Double = 0.0,
    val unit: String?,
    val gstPercentage: Double?,
    val description: String?,
    val hsnCode: String?,
    val updatedAt: Long = System.currentTimeMillis()
) {
    fun getPriceWithGst(): Double {
        return price + (price * (gstPercentage ?: 0.0) / 100)
    }
}

class InvalidProductException(message: String): Exception(message)