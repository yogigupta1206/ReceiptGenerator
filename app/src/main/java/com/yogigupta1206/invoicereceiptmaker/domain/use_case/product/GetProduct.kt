package com.yogigupta1206.invoicereceiptmaker.domain.use_case.product

import com.yogigupta1206.invoicereceiptmaker.domain.repository.ProductRepository

class GetProduct(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(id: Long) = productRepository.getProductById(id)

}