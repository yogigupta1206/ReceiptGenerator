package com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product (
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String? = null,
    val price: Double = 0.0,
    val unit: String? = null,
    val gstPercentage: Double? = null,
    val description: String? = null,
    val hsnCode: String? = null,
    val updatedAt: Long = System.currentTimeMillis()
) {
    fun getPriceWithGst(): Double {
        return price + (price * (gstPercentage ?: 0.0) / 100)
    }
}

class InvalidProductException(message: String): Exception(message)