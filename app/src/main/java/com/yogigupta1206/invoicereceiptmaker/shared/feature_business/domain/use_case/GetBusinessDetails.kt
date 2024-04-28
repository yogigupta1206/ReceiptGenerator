package com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.repository.BusinessRepository

class GetBusinessDetails(
    private val businessRepository: BusinessRepository
) {
    suspend operator fun invoke() = businessRepository.getBusinessDetails()
}