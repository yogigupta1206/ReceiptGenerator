package com.yogigupta1206.invoicereceiptmaker.domain.use_case.product

data class ProductUseCases(
    val getProducts: GetProducts,
    val getProduct: GetProduct,
    val deleteProduct: DeleteProduct,
    val addProduct: AddProduct
)
