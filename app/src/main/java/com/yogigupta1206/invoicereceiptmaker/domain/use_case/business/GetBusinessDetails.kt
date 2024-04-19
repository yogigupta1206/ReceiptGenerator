package com.yogigupta1206.invoicereceiptmaker.domain.use_case.business

import com.yogigupta1206.invoicereceiptmaker.domain.repository.BusinessRepository

class GetBusinessDetails(
    private val businessRepository: BusinessRepository
) {
    suspend operator fun invoke() = businessRepository.getBusinessDetails()
}