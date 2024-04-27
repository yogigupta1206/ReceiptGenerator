package com.yogigupta1206.invoicereceiptmaker.domain.use_case.quotation

class VerifyOtherCharges {

    @Throws(Exception::class)
    operator fun invoke(
        chargesLabel: String,
        chargesAmount: String,
        chargesTax: String,
        isTaxable: Boolean
    ) {
        if (chargesLabel.isBlank()) {
            throw Exception("Please enter charges label")
        }
        if (chargesAmount.isBlank()) {
            throw Exception("Please enter charges amount")
        }
        if (chargesAmount.toDouble() <= 0.0) {
            throw Exception("Charges amount cannot be negative or 0")
        }
        if (isTaxable) {
            if (chargesTax.isBlank()) {
                throw Exception("Please enter charges tax")
            }
            if (chargesTax.toDouble() <= 0.0) {
                throw Exception("Charges tax cannot be negative or 0")
            }
        }
    }

}