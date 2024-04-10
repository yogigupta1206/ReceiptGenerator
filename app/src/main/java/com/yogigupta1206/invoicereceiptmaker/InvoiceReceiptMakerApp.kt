package com.yogigupta1206.invoicereceiptmaker

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InvoiceReceiptMakerApp: Application(){
    companion object {
        private const val TAG = "InvoiceReceiptMakerApp"
    }

    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, "onCreate: InvoiceReceiptMakerApp")
    }

}