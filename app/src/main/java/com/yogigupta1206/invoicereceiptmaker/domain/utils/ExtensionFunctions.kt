package com.yogigupta1206.invoicereceiptmaker.domain.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long?.toCurrentDataAsString(pattern: String? = "dd-MM-yyyy"): String {
    return this?.let {
        val date = Date(it)
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        format.format(date)
    } ?: ""
}