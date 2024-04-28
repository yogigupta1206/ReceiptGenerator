package com.yogigupta1206.invoicereceiptmaker.shared.feature_tnc.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TnC(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val terms: String
)
