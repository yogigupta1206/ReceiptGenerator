package com.yogigupta1206.invoicereceiptmaker.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Customer::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("customerId"),
        onDelete = ForeignKey.CASCADE // Optional: Delete quotation when customer is deleted
    )], indices = [Index(value = ["customerId"])]
)
data class Quotation(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val customerId: Long? = null,
    val otherChargesLabel: String? = "Other Charge",
    val otherCharges: Double = 0.0,
    val otherChargesTaxable: Boolean = false,
    val otherChargesTax: Double = 0.0,
    val quotationTime: Long = System.currentTimeMillis(),
    val quotationTotal: Double = 0.0,
    val quotationComplete: Boolean = false
)

@Entity(
    foreignKeys = [ForeignKey(
        entity = TnC::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("termsId"),
        onDelete = ForeignKey.CASCADE // Optional: Delete quotation terms when terms are deleted
    ), ForeignKey(
        entity = Quotation::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("quotationId"),
        onDelete = ForeignKey.CASCADE // Optional: Delete quotation terms when quotation is deleted
    )],
    indices = [Index(value = ["termsId"])]
)
data class QuotationTerms(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val quotationId: Long, val termsId: Long
)

@Entity(
    foreignKeys = [ForeignKey(
        entity = Product::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("productId"),
        onDelete = ForeignKey.CASCADE // Optional: Delete quotation items when product is deleted
    ), ForeignKey(
        entity = Quotation::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("quotationId"),
        onDelete = ForeignKey.CASCADE // Optional: Delete quotation items when quotation is deleted
    )],
    indices = [Index(value = ["productId"])]
)
data class QuotationItem(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val quotationId: Long = -1,
    val productId: Long,
    var quantity: Int = 1,
    val price: Long,
    val gst: Double = 0.0,
    val discountType: QuotationDiscountType = QuotationDiscountType.PERCENTAGE,
    val discount: Double = 0.0,
    val description: String,
)


enum class QuotationDiscountType {
    PERCENTAGE, FLAT
}