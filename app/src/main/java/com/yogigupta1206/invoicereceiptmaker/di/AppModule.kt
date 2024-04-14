package com.yogigupta1206.invoicereceiptmaker.di

import android.app.Application
import androidx.room.Room
import com.yogigupta1206.invoicereceiptmaker.data.data_source.db.AppDbDataSource
import com.yogigupta1206.invoicereceiptmaker.data.data_source.prefs.AppPrefDataSource
import com.yogigupta1206.invoicereceiptmaker.data.repository.BusinessRepositoryImpl
import com.yogigupta1206.invoicereceiptmaker.data.repository.CustomerRepositoryImpl
import com.yogigupta1206.invoicereceiptmaker.data.repository.ProductRepositoryImpl
import com.yogigupta1206.invoicereceiptmaker.data.repository.TnCRepositoryImpl
import com.yogigupta1206.invoicereceiptmaker.domain.repository.BusinessRepository
import com.yogigupta1206.invoicereceiptmaker.domain.repository.CustomerRepository
import com.yogigupta1206.invoicereceiptmaker.domain.repository.ProductRepository
import com.yogigupta1206.invoicereceiptmaker.domain.repository.TnCRepository
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
    fun provideAppPrefDataSource(): AppPrefDataSource {
        return AppPrefDataSource()
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
    fun provideBusinessRepository(appPrefDataSource: AppPrefDataSource) : BusinessRepository {
        return BusinessRepositoryImpl(appPrefDataSource)
    }

    @Provides
    fun provideTnCRepository(appDbDataSource: AppDbDataSource) : TnCRepository {
        return TnCRepositoryImpl(appDbDataSource.tnCDao())
    }



}