package com.yogigupta1206.invoicereceiptmaker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TnC(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val terms: String
)
