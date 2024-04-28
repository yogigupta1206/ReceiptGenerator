package com.yogigupta1206.invoicereceiptmaker.di

import android.app.Application
import androidx.room.Room
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.data.repository.QuotationRepositoryImpl
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.repository.QuotationRepository
import com.yogigupta1206.invoicereceiptmaker.shared.data_source.db.AppDbDataSource
import com.yogigupta1206.invoicereceiptmaker.shared.data_source.prefs.AppPrefDataSource
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.data.repository.BusinessRepositoryImpl
import com.yogigupta1206.invoicereceiptmaker.shared.feature_business.domain.repository.BusinessRepository
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.data.repository.CustomerRepositoryImpl
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.repository.CustomerRepository
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.data.repository.ProductRepositoryImpl
import com.yogigupta1206.invoicereceiptmaker.shared.feature_product.domain.repository.ProductRepository
import com.yogigupta1206.invoicereceiptmaker.shared.feature_tnc.data.repository.TnCRepositoryImpl
import com.yogigupta1206.invoicereceiptmaker.shared.feature_tnc.domain.repository.TnCRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAppPrefDataSource(app:Application): AppPrefDataSource {
        return AppPrefDataSource(app)
    }

    @Provides
    @Singleton
    fun provideAppDbDataSource(app: Application): AppDbDataSource {
        return Room
            .databaseBuilder(
                app,
                AppDbDataSource::class.java,
                AppDbDataSource.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }

    @Provides
    fun provideProductRepository(appDbDataSource: AppDbDataSource) : ProductRepository {
        return ProductRepositoryImpl(appDbDataSource.productsDao())
    }

    @Provides
    fun provideCustomerRepository(appDbDataSource: AppDbDataSource) : CustomerRepository {
        return CustomerRepositoryImpl(appDbDataSource.customersDao())
    }

    @Provides
    fun provideBusinessRepository(appPrefDataSource: AppPrefDataSource): BusinessRepository {
        return BusinessRepositoryImpl(appPrefDataSource)
    }

    @Provides
    fun provideTnCRepository(appDbDataSource: AppDbDataSource): TnCRepository {
        return TnCRepositoryImpl(appDbDataSource.tnCDao())
    }

    @Provides
    fun provideQuotationRepository(appDbDataSource: AppDbDataSource): QuotationRepository {
        return QuotationRepositoryImpl(appDbDataSource.quotationDao())
    }


}