package com.yogigupta1206.invoicereceiptmaker.feature_quotation.presentation.make_quotation

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItemWithProduct
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer

sealed class MakeQuotationEvent {

    data object AddProduct : MakeQuotationEvent()
    data class AddCustomer(val customer: Customer) : MakeQuotationEvent()
    data class UpdateQuotationDate(val date: Long) : MakeQuotationEvent()
    data class EnteredOtherChargesLabel(val label: String) : MakeQuotationEvent()
    data class EnteredOtherChargesValue(val value: String) : MakeQuotationEvent()
    data class EnteredOtherChargesTax(val tax: String) : MakeQuotationEvent()
    data class EnteredOtherChargesIsTaxable(val isTaxable: Boolean) : MakeQuotationEvent()
    data class DeleteProduct(val quotationItemWithProduct: QuotationItemWithProduct) :
        MakeQuotationEvent()

    data object ClickedOtherChargesPlusButton : MakeQuotationEvent()
    data object ClickedCustomerPlusButton : MakeQuotationEvent()

    data object ClickedBottomSheetDismiss : MakeQuotationEvent()
    data object UpdateBottonSheet : MakeQuotationEvent()
    data object GenerateQuotation : MakeQuotationEvent()
    data object DeleteOtherCharges : MakeQuotationEvent()


}