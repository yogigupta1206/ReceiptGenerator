package com.yogigupta1206.invoicereceiptmaker.shared.feature_business.presentation.business

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.model.Business
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.use_case.BusinessUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BusinessViewModel @Inject constructor(
    private val businessUseCases: BusinessUseCases
) : ViewModel() {

    companion object {
        private const val TAG = "BusinessViewModel"
    }

    private val _businessName = mutableStateOf<String?>("Business Name")
    val businessName: State<String?> = _businessName

    private val _contactName = mutableStateOf<String?>("Contact Name")
    val contactName: State<String?> = _contactName

    private val _email = mutableStateOf<String?>("Email@email.com")
    val email: State<String?> = _email

    private val _phone = mutableStateOf<String?>("Phone Number")
    val phone: State<String?> = _phone

    private val _address1 = mutableStateOf<String?>("Address 1")
    val address1: State<String?> = _address1

    private val _address2 = mutableStateOf<String?>("Address 2")
    val address2: State<String?> = _address2

    private val _address3 = mutableStateOf<String?>("Address 3")
    val address3: State<String?> = _address3

    private val _otherInfo = mutableStateOf<String?>("Other Info")
    val otherInfo: State<String?> = _otherInfo

    private val _gstPanVanLabel = mutableStateOf<String?>("GSTIN")
    val gstPanVanLabel: State<String?> = _gstPanVanLabel

    private val _gstPanVanNumber = mutableStateOf<String?>("GSTIN Number")
    val gstPanVanNumber: State<String?> = _gstPanVanNumber

    private val _businessCategory = mutableStateOf<String?>("Business Category")
    val businessCategory: State<String?> = _businessCategory

    private val _paymentDetails = mutableStateOf<String?>("Payment Details")
    val paymentDetails: State<String?> = _paymentDetails

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            businessUseCases.getBusinessDetails().let { businessDetails ->
                _businessName.value = businessDetails.businessName
                _contactName.value = businessDetails.contactName
                _email.value = businessDetails.email
                _phone.value = businessDetails.phone
                _address1.value = businessDetails.address1
                _address2.value = businessDetails.address2
                _address3.value = businessDetails.address3
                _otherInfo.value = businessDetails.otherInfo
                _gstPanVanLabel.value = businessDetails.gstPanVanLabel
                _gstPanVanNumber.value = businessDetails.gstPanVanNumber
                _businessCategory.value = businessDetails.businessCategory
                _paymentDetails.value = businessDetails.paymentDetails
            }
        }
    }

    fun onEvent(event: BusinessScreenEvent) {
        when (event) {
            is BusinessScreenEvent.EnteredBusinessName -> _businessName.value = event.businessName
            is BusinessScreenEvent.EnteredContactName -> _contactName.value = event.contactName
            is BusinessScreenEvent.EnteredEmail -> _email.value = event.email
            is BusinessScreenEvent.EnteredPhone -> _phone.value = event.phone
            is BusinessScreenEvent.EnteredAddress1 -> _address1.value = event.address1
            is BusinessScreenEvent.EnteredAddress2 -> _address2.value = event.address2
            is BusinessScreenEvent.EnteredAddress3 -> _address3.value = event.address3
            is BusinessScreenEvent.EnteredOtherInfo -> _otherInfo.value = event.otherInfo
            is BusinessScreenEvent.EnteredGstPanVanLabel -> _gstPanVanLabel.value =
                event.gstPanVanLabel

            is BusinessScreenEvent.EnteredGstPanVanNumber -> _gstPanVanNumber.value =
                event.gstPanVanNumber

            is BusinessScreenEvent.EnteredBusinessCategory -> _businessCategory.value =
                event.businessCategory

            is BusinessScreenEvent.EnteredPaymentDetails -> _paymentDetails.value =
                event.paymentDetails

            is BusinessScreenEvent.SaveBusinessDetails -> saveBusinessDetails()
        }
    }

    private fun saveBusinessDetails() {
        viewModelScope.launch {
            try {
                val businessData = Business(
                    businessName = businessName.value ?: "",
                    contactName = contactName.value ?: "",
                    email = email.value ?: "",
                    phone = phone.value ?: "",
                    address1 = address1.value ?: "",
                    address2 = address2.value ?: "",
                    address3 = address3.value ?: "",
                    otherInfo = otherInfo.value ?: "",
                    gstPanVanLabel = gstPanVanLabel.value ?: "",
                    gstPanVanNumber = gstPanVanNumber.value ?: "",
                    businessCategory = businessCategory.value ?: "",
                    paymentDetails = paymentDetails.value ?: ""
                )
                Log.d(TAG, "saveBusinessDetails: $businessData")
                withContext(Dispatchers.IO){ businessUseCases.addBusinessDetails(businessData) }
                _eventFlow.emit(UiEvent.SaveDetails)
            } catch (e: Exception) {
                _eventFlow.emit(
                    UiEvent.ShowSnackBar("Error: ${e.localizedMessage}")
                )
            }

        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        data object SaveDetails : UiEvent()
    }


}