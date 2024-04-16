package com.yogigupta1206.invoicereceiptmaker.domain.repository

import com.yogigupta1206.invoicereceiptmaker.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<List<Product>>

    suspend fun getProductById(id: Long): Product?

    suspend fun insertProduct(product: Product)

    suspend fun insertAllProducts(products: List<Product>)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)

    suspend fun deleteProductById(id: Long)

    suspend fun deleteAllProducts()
}