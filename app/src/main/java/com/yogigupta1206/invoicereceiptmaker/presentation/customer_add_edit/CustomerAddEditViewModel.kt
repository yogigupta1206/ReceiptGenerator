package com.yogigupta1206.invoicereceiptmaker.presentation.customer_add_edit

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogigupta1206.invoicereceiptmaker.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.customer.CustomerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerAddEditViewModel @Inject constructor(
    private val customerUseCases: CustomerUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val TAG = "CustomerAddEditViewModel"
    }

    private val _customerName = mutableStateOf<String>(String())
    val customerName: State<String> = _customerName

    private val _customerEmail = mutableStateOf<String>(String())
    val customerEmail: State<String> = _customerEmail

    private val _customerPhone = mutableStateOf<String>(String())
    val customerPhone: State<String> = _customerPhone

    private val _customerGstNumber = mutableStateOf<String>(String())
    val customerGstNumber: State<String> = _customerGstNumber

    private val _customerAddress1 = mutableStateOf<String>(String())
    val customerAddress1: State<String> = _customerAddress1

    private val _customerAddress2 = mutableStateOf<String>(String())
    val customerAddress2: State<String> = _customerAddress2

    private val _customerOtherInfo = mutableStateOf<String>(String())
    val customerOtherInfo: State<String> = _customerOtherInfo

    private val _customerShippingAddress = mutableStateOf<String>(String())
    val customerShippingAddress: State<String> = _customerShippingAddress

    var customerId: Long? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Long>("customerId")?.let { customerId ->
            if (customerId != -1L) {
                this.customerId = customerId
                viewModelScope.launch {
                    customerUseCases.getCustomer(customerId)?.also { customer ->
                        _customerName.value = customer.name ?: ""
                        _customerEmail.value = customer.email ?: ""
                        _customerPhone.value = customer.phone ?: ""
                        _customerGstNumber.value = customer.gstNumber ?: ""
                        _customerAddress1.value = customer.address1 ?: ""
                        _customerAddress2.value = customer.address2 ?: ""
                        _customerOtherInfo.value = customer.otherInfo ?: ""
                        _customerShippingAddress.value = customer.shippingAddress ?: ""
                    }
                }
            }
        }
    }

    fun onEvent(event: CustomerAddEditEvent) {
        when (event) {
            is CustomerAddEditEvent.EnteredName -> {
                _customerName.value = event.name
            }

            is CustomerAddEditEvent.EnteredEmail -> {
                _customerEmail.value = event.email
            }

            is CustomerAddEditEvent.EnteredPhone -> {
                _customerPhone.value = event.phone
            }

            is CustomerAddEditEvent.EnteredGstNumber -> {
                _customerGstNumber.value = event.gstNumber
            }

            is CustomerAddEditEvent.EnteredAddress1 -> {
                _customerAddress1.value = event.address1
            }

            is CustomerAddEditEvent.EnteredAddress2 -> {
                _customerAddress2.value = event.address2
            }

            is CustomerAddEditEvent.EnteredOtherInfo -> {
                _customerOtherInfo.value = event.otherInfo
            }

            is CustomerAddEditEvent.EnteredShippingAddress -> {
                _customerShippingAddress.value = event.shippingAddress
            }

            CustomerAddEditEvent.DeleteCustomer -> {
                viewModelScope.launch {
                    customerId?.let {
                        customerUseCases.deleteCustomer(it)
                    }
                }
            }

            CustomerAddEditEvent.SaveCustomer -> {
                viewModelScope.launch {
                    Log.d(TAG, "customer ID: $customerId")
                    try {
                        val customer = Customer(
                            id = customerId,
                            name = _customerName.value,
                            email = _customerEmail.value,
                            phone = _customerPhone.value,
                            gstNumber = _customerGstNumber.value,
                            address1 = _customerAddress1.value,
                            address2 = _customerAddress2.value,
                            otherInfo = _customerOtherInfo.value,
                            shippingAddress = _customerShippingAddress.value,
                            updatedAt = System.currentTimeMillis()
                        )
                        customerUseCases.addCustomer(customer)
                        _eventFlow.emit(UiEvent.SaveCustomerDetails)
                    } catch (e: Exception) {
                        _eventFlow.emit(UiEvent.ShowSnackbar("Invalid: ${e.message}"))
                    }
                }

            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data object SaveCustomerDetails : UiEvent()
    }

}