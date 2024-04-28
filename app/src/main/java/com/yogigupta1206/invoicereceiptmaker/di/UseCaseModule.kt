package com.yogigupta1206.invoicereceiptmaker.di

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.repository.QuotationRepository
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.AddQuotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.AddQuotationProduct
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.DeleteQuotationItemById
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.GetAllProductsOfQuotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.GetCustomerOfQuotationId
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.GetQuotationWithId
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.QuotationUseCases
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.SaveQuotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.UpdateQuotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.use_case.VerifyOtherCharges
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.repository.BusinessRepository
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.use_case.AddBusinessDetails
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.use_case.BusinessUseCases
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.use_case.GetBusinessDetails
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.repository.CustomerRepository
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.use_case.AddCustomer
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.use_case.CustomerUseCases
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.use_case.DeleteCustomer
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.use_case.GetCustomer
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.use_case.GetCustomers
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.repository.ProductRepository
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.use_case.AddProduct
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.use_case.DeleteProduct
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.use_case.GetProduct
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.use_case.GetProducts
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.use_case.ProductUseCases
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
    ): BusinessUseCases {
        return BusinessUseCases(
            GetBusinessDetails(businessRepository),
            AddBusinessDetails(businessRepository)
        )
    }


    @Provides
    @Singleton
    fun provideQuotationUseCases(
        quotationRepository: QuotationRepository,
    ): QuotationUseCases {
        return QuotationUseCases(
            AddQuotation(quotationRepository),
            GetCustomerOfQuotationId(quotationRepository),
            UpdateQuotation(quotationRepository),
            GetAllProductsOfQuotation(quotationRepository),
            SaveQuotation(quotationRepository),
            VerifyOtherCharges(),
            GetQuotationWithId(quotationRepository),
            AddQuotationProduct(quotationRepository),
            DeleteQuotationItemById(quotationRepository)
        )
    }

}