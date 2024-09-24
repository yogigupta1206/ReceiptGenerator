package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.model.Product
import com.yogigupta1206.invoicereceiptmaker.shared.feature_tnc.domain.model.TnC

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
    val otherChargesLabel: String? = "",
    val otherCharges: Double = 0.0,
    val otherChargesTaxable: Boolean = false,
    val otherChargesTax: Double = 0.0,
    val quotationTime: Long = System.currentTimeMillis(),
    val totalAmount: Double = 0.0,
    val totalGst: Double = 0.0,
    val quotationComplete: Boolean = false,
    val quotationPath: String? = null
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
    val productId: Long = -1,
    var quantity: Int = 1,
    val price: Double = 0.0,
    val gst: Double = 0.0,
    val discountType: DiscountType = DiscountType.PERCENTAGE,
    val discount: Double = 0.0,
    val description: String = "",
    val totalGst: Double = 0.0,
    val totalAmount: Double = 0.0
)


enum class DiscountType {
    PERCENTAGE, FLAT
}