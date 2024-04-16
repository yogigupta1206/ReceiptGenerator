package com.yogigupta1206.invoicereceiptmaker.domain.use_case.product

import com.yogigupta1206.invoicereceiptmaker.domain.model.InvalidProductException
import com.yogigupta1206.invoicereceiptmaker.domain.model.Product
import com.yogigupta1206.invoicereceiptmaker.domain.repository.ProductRepository
import kotlin.jvm.Throws

class AddProduct (
    private val productRepository: ProductRepository
){
    @Throws(InvalidProductException::class)
    suspend operator fun invoke(product: Product) {
        if(product.name.isBlank()) {
            throw InvalidProductException("Product name can't be empty")
        }
        if(product.price <= 0) {
            throw InvalidProductException("Product price can't be less than or equal to 0")
        }
        if(product.gstPercentage != null && product.gstPercentage < 0) {
            throw InvalidProductException("Product GST percentage can't be less than 0")
        }
        productRepository.insertProduct(product)
    }
}