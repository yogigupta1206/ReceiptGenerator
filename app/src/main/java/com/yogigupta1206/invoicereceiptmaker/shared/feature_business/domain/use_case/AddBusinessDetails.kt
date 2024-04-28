package com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.model.Business
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.model.InvalidBusinessException
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.repository.BusinessRepository
import com.yogigupta1206.invoicereceiptmaker.shared.utils.UserDetailsValidator

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