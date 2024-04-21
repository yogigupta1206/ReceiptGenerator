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
    var customerId: Long,
    var otherChargesLabel: String? = "Other Charge",
    var otherCharges: Double = 0.0,
    var otherChargesTaxable: Boolean = false,
    var quotationTime: Long = System.currentTimeMillis(),
    var quotationTotal: Double = 0.0
)

@Entity(
    primaryKeys = ["quotationId", "termsId"], foreignKeys = [ForeignKey(
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
    val quotationId: Long, val termsId: Long
)

@Entity(
    primaryKeys = ["quotationId", "productId"], foreignKeys = [ForeignKey(
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
    var quotationId: Long = -1,
    val productId: Long,
    var quantity: Int = 1,
    var discountType: QuotationDiscountType = QuotationDiscountType.PERCENTAGE,
    var discount: Double = 0.0,
)


enum class QuotationDiscountType {
    PERCENTAGE, FLAT
}