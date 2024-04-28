package com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.DiscountType
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.repository.QuotationRepository

class AddQuotationProduct(
    private val quotationRepository: QuotationRepository
) {

    suspend operator fun invoke(quotationItem: QuotationItem) {
        if (quotationItem.quotationId == -1L)
            throw IllegalArgumentException("Quotation Id is missing")
        if (quotationItem.productId == -1L)
            throw IllegalArgumentException("Product Id is missing")
        if (quotationItem.quantity == 0)
            throw IllegalArgumentException("Quantity cannot be 0")
        if (quotationItem.quantity < 0)
            throw IllegalArgumentException("Quantity cannot be negative")
        if (quotationItem.gst < 0)
            throw IllegalArgumentException("GST Percentage cannot be negative")
        if (quotationItem.discount < 0)
            throw IllegalArgumentException("Discount cannot be negative")
        if (quotationItem.discountType == DiscountType.PERCENTAGE && quotationItem.discount > 100)
            throw IllegalArgumentException("Discount Percentage cannot be greater than 100")

        quotationRepository.addQuotationProduct(quotationItem)
    }
}