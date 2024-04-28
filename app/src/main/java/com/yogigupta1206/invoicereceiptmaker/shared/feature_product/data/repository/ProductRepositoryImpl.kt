package com.yogigupta1206.invoicereceiptmaker.shared.feature_product.data.repository

import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.data.data_source.ProductsDao
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.model.Product
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val productsDao: ProductsDao
): ProductRepository {
    override fun getAllProducts(): Flow<List<Product>> {
        return productsDao.getAllProducts()
    }

    override suspend fun getProductById(id: Long): Product? {
        return productsDao.getProductById(id)
    }

    override suspend fun insertProduct(product: Product) {
        return productsDao.insert(product)
    }

    override suspend fun insertAllProducts(products: List<Product>) {
        return productsDao.insertAll(products)
    }

    override suspend fun updateProduct(product: Product) {
        return productsDao.update(product)
    }

    override suspend fun deleteProduct(product: Product) {
        return productsDao.deleteProduct(product)
    }

    override suspend fun deleteProductById(id: Long) {
        return productsDao.deleteProductById(id)
    }

    override suspend fun deleteAllProducts() {
        return productsDao.deleteAllProducts()
    }
}