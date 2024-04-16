package com.yogigupta1206.invoicereceiptmaker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product (
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String,
    val price: Double,
    val unit: String?,
    val gstPercentage: Double?,
    val description: String?,
    val hsnCode: String?
) {
    fun getPriceWithGst(): Double {
        return price + (price * (gstPercentage ?: 0.0) / 100)
    }
}