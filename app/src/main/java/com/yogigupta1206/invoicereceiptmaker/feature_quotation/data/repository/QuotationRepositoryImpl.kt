package com.yogigupta1206.invoicereceiptmaker.feature_quotation.data.repository

import com.yogigupta1206.invoicereceiptmaker.feature_quotation.data.data_source.QuotationDao
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.Quotation
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItem
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationItemWithProduct
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.model.QuotationWithCustomer
import com.yogigupta1206.invoicereceiptmaker.feature_quotation.domain.repository.QuotationRepository
import com.yogigupta1206.invoicereceiptmaker.shared.feature_customer.domain.model.Customer
import kotlinx.coroutines.flow.Flow

class QuotationRepositoryImpl(
    private val quotationDao: QuotationDao
) : QuotationRepository {

    override suspend fun saveQuotation(quotation: Quotation, itemList: List<QuotationItem>) {
        quotationDao.insertQuotation(quotation)
        val quotationItems = itemList.map { it.copy(quotationId = quotation.id ?: -1) }
        quotationDao.insertQuotationItems(quotationItems)
    }

    override suspend fun addQuotation(quotation: Quotation): Long {
        return quotationDao.insertQuotationAndGetId(quotation)
    }

    override fun getQuotationById(quotationId: Long): Flow<Quotation> {
        return quotationDao.getQuotationById(quotationId)
    }

    override suspend fun deleteQuotationById(quotationId: Long) {
        quotationDao.deleteQuotationById(quotationId)
    }

    override suspend fun updateQuotation(quotation: Quotation, itemList: List<QuotationItem>) {
        quotationDao.updateQuotation(quotation)

        // Delete all items and insert new items
        quotation.id?.let { quotationId ->
            quotationDao.deleteQuotationItemsByQuotationId(quotationId)
            val quotationItems = itemList.map { it.copy(quotationId = quotationId) }
            quotationDao.insertQuotationItems(quotationItems)
        }
    }

    fun getQuotationWithCustomer(quotationId: Long): Flow<QuotationWithCustomer> {
        return quotationDao.getQuotationWithCustomerDetails(quotationId)
    }

    override fun getQuotationAndQuotationItemsById(quotationId: Long): Flow<List<QuotationItemWithProduct>> {
        return quotationDao.getQuotationItemWithProductByQuotationId(quotationId)
    }

    override fun getAllProductsOfQuotation(quotationId: Long): Flow<List<QuotationItem>> {
        return quotationDao.getAllProductsOfQuotation(quotationId)
    }

    override fun getCustomerOfQuotationId(quotationId: Long): Flow<Customer> {
        return quotationDao.getCustomerOfQuotationId(quotationId)
    }

}