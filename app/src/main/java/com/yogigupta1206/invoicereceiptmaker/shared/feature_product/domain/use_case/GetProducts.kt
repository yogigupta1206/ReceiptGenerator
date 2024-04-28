package com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.use_case

import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.model.Product
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProducts(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): Flow<List<Product>> {
        return productRepository.getAllProducts()
    }

}