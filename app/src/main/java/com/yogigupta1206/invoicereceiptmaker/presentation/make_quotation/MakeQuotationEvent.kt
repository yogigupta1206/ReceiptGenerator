package com.yogigupta1206.invoicereceiptmaker.presentation.make_quotation

import com.yogigupta1206.invoicereceiptmaker.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationItemWithProduct

sealed class MakeQuotationEvent {

    data class AddProduct(val quotationItemWithProduct: QuotationItemWithProduct) :
        MakeQuotationEvent()

    data class AddCustomer(val customer: Customer) : MakeQuotationEvent()
    data class UpdateQuotationDate(val date: Long) : MakeQuotationEvent()
    data class EnteredOtherChargesLabel(val label: String) : MakeQuotationEvent()
    data class EnteredOtherChargesValue(val value: String) : MakeQuotationEvent()
    data class EnteredOtherChargesTax(val tax: String) : MakeQuotationEvent()
    data class EnteredOtherChargesTaxable(val taxable: Boolean) : MakeQuotationEvent()
    data class DeleteProduct(val quotationItemWithProduct: QuotationItemWithProduct) :
        MakeQuotationEvent()

    data object GenerateQuotation : MakeQuotationEvent()


}