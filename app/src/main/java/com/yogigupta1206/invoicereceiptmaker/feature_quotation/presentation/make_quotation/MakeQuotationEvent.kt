package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation

sealed class MakeQuotationEvent {

    // Product Events
    data object ClickedProductPlusButton : MakeQuotationEvent()
    data class DeleteProduct(val productId: Long) : MakeQuotationEvent()


    // Other Charges Events
    data object ClickedOtherChargesPlusButton : MakeQuotationEvent()
    data class EnteredOtherChargesLabel(val label: String) : MakeQuotationEvent()
    data class EnteredOtherChargesValue(val value: String) : MakeQuotationEvent()
    data class EnteredOtherChargesTax(val tax: String) : MakeQuotationEvent()
    data class EnteredOtherChargesIsTaxable(val isTaxable: Boolean) : MakeQuotationEvent()
    data object ClickedBottomSheetDismiss : MakeQuotationEvent()
    data object DeleteOtherCharges : MakeQuotationEvent()
    data object UpdateBottomSheet : MakeQuotationEvent()


    // Customer Events
    data object ClickedCustomerPlusButton : MakeQuotationEvent()
    data object DeleteCustomer : MakeQuotationEvent()


    data object GenerateQuotation : MakeQuotationEvent()
    data class UpdateQuotationDate(val date: Long) : MakeQuotationEvent()


}