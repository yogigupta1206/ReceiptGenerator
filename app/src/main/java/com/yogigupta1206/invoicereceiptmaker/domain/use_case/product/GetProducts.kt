package com.yogigupta1206.invoicereceiptmaker.domain.use_case.product

import com.yogigupta1206.invoicereceiptmaker.domain.model.Product
import com.yogigupta1206.invoicereceiptmaker.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProducts(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): Flow<List<Product>> {
        return productRepository.getAllProducts()
    }

}