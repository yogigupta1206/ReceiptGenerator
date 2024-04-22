package com.yogigupta1206.invoicereceiptmaker.presentation.make_quotation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.yogigupta1206.invoicereceiptmaker.domain.model.Customer
import com.yogigupta1206.invoicereceiptmaker.domain.model.QuotationItemWithProduct
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.quotation.QuotationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MakeQuotationViewModel @Inject constructor(
    private val quotationUseCases: QuotationUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val TAG = "MakeQuotationViewModel"
    }

    private val _quotationItemList = mutableStateOf<List<QuotationItemWithProduct>>(emptyList())
    val quotationItemList: State<List<QuotationItemWithProduct>> = _quotationItemList

    private val _customer = mutableStateOf<Customer?>(null)
    val customer: State<Customer?> = _customer

    private val _otherChargesLabel = mutableStateOf<String>(String())
    val otherChargesLabel: State<String> = _otherChargesLabel

    private val _otherChargesValue = mutableStateOf<String>(String())
    val otherChargesValue: State<String> = _otherChargesValue

    private val _otherChargesTaxable = mutableStateOf<Boolean>(false)
    val otherChargesTaxable: State<Boolean> = _otherChargesTaxable

    private val _otherChargesTax = mutableStateOf<String>(String())
    val otherChargesTax: State<String> = _otherChargesTax

    private val _quotationTime = mutableLongStateOf(System.currentTimeMillis())
    val quotationTime: State<Long> = _quotationTime


    fun onEvent(event: MakeQuotationEvent) {
        when (event) {
            is MakeQuotationEvent.AddCustomer -> {
                _customer.value = event.customer
            }

            is MakeQuotationEvent.AddProduct -> TODO()
            is MakeQuotationEvent.DeleteProduct -> TODO()
            is MakeQuotationEvent.EnteredOtherChargesLabel -> {
                _otherChargesLabel.value = event.label
            }

            is MakeQuotationEvent.EnteredOtherChargesTax -> {
                _otherChargesTax.value = event.tax
            }

            is MakeQuotationEvent.EnteredOtherChargesTaxable -> {
                _otherChargesTaxable.value = event.taxable
            }

            is MakeQuotationEvent.EnteredOtherChargesValue -> {
                _otherChargesValue.value = event.value
            }

            is MakeQuotationEvent.UpdateQuotationDate -> {
                _quotationTime.longValue = event.date
            }

            MakeQuotationEvent.GenerateQuotation -> TODO()

        }
    }


}