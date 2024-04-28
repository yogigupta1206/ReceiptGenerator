package com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.use_case

data class ProductUseCases(
    val getProducts: GetProducts,
    val getProduct: GetProduct,
    val deleteProduct: DeleteProduct,
    val addProduct: AddProduct
)
