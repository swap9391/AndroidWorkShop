package com.lorem.androidworkshop.ui.features.quotes.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val quoteText: String,
    val author: String
)