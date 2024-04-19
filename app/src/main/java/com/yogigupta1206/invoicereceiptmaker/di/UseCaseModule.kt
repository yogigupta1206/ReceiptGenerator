package com.yogigupta1206.invoicereceiptmaker.di

import com.yogigupta1206.invoicereceiptmaker.domain.repository.BusinessRepository
import com.yogigupta1206.invoicereceiptmaker.domain.repository.CustomerRepository
import com.yogigupta1206.invoicereceiptmaker.domain.repository.ProductRepository
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.business.AddBusinessDetails
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.business.BusinessUseCases
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.business.GetBusinessDetails
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.customer.AddCustomer
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.customer.CustomerUseCases
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.customer.DeleteCustomer
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.customer.GetCustomer
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.customer.GetCustomers
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.product.AddProduct
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.product.DeleteProduct
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.product.GetProduct
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.product.GetProducts
import com.yogigupta1206.invoicereceiptmaker.domain.use_case.product.ProductUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideCustomerUseCases(
        customerRepository: CustomerRepository,
    ): CustomerUseCases {
        return CustomerUseCases(
            GetCustomers(customerRepository),
            GetCustomer(customerRepository),
            DeleteCustomer(customerRepository),
            AddCustomer(customerRepository)
        )
    }

    @Provides
    @Singleton
    fun provideProductUseCases(
        productRepository: ProductRepository,
    ): ProductUseCases {
        return ProductUseCases(
            GetProducts(productRepository),
            GetProduct(productRepository),
            DeleteProduct(productRepository),
            AddProduct(productRepository)
        )
    }

    @Provides
    @Singleton
    fun provideBusinessUseCases(
        businessRepository: BusinessRepository,
    ): BusinessUseCases{
        return BusinessUseCases(
            GetBusinessDetails(businessRepository),
            AddBusinessDetails(businessRepository)
        )
    }


}