package com.yogigupta1206.invoicereceiptmaker.presentation.business

sealed class BusinessScreenEvent {
    data class EnteredBusinessName(val businessName: String) : BusinessScreenEvent()
    data class EnteredContactName(val contactName: String) : BusinessScreenEvent()
    data class EnteredEmail(val email: String) : BusinessScreenEvent()
    data class EnteredPhone(val phone: String) : BusinessScreenEvent()
    data class EnteredAddress1(val address1: String) : BusinessScreenEvent()
    data class EnteredAddress2(val address2: String) : BusinessScreenEvent()
    data class EnteredAddress3(val address3: String) : BusinessScreenEvent()
    data class EnteredOtherInfo(val otherInfo: String) : BusinessScreenEvent()
    data class EnteredGstPanVanLabel(val gstPanVanLabel: String) : BusinessScreenEvent()
    data class EnteredGstPanVanNumber(val gstPanVanNumber: String) : BusinessScreenEvent()
    data class EnteredBusinessCategory(val businessCategory: String) : BusinessScreenEvent()
    data class EnteredPaymentDetails(val paymentDetails: String) : BusinessScreenEvent()
    data object SaveBusinessDetails : BusinessScreenEvent()


}