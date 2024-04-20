package com.yogigupta1206.invoicereceiptmaker.domain.use_case.business

import com.yogigupta1206.invoicereceiptmaker.domain.model.Business
import com.yogigupta1206.invoicereceiptmaker.domain.model.InvalidBusinessException
import com.yogigupta1206.invoicereceiptmaker.domain.repository.BusinessRepository
import com.yogigupta1206.invoicereceiptmaker.domain.utils.UserDetailsValidator

class AddBusinessDetails(
    private val businessRepository: BusinessRepository
) {
    @Throws(InvalidBusinessException::class)
    suspend operator fun invoke(business: Business) {
        if(business.businessName.isNullOrBlank() && business.contactName.isNullOrBlank())
            throw InvalidBusinessException("Name can't be empty or blank")
        if(!business.email.isNullOrBlank() && !UserDetailsValidator.isValidEmail(business.email))
            throw InvalidBusinessException("Invalid email")
        businessRepository.addBusinessDetails(business)
    }
}