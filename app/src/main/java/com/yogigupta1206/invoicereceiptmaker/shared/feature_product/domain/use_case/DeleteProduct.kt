package com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.repository.ProductRepository

class DeleteProduct(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(id: Long) = productRepository.deleteProductById(id)
}