package com.yogigupta1206.invoicereceiptmaker.domain.utils

object UserDetailsValidator {

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}