package com.lorem.androidworkshop.ui.features.quotes.domain.repository

import com.lorem.androidworkshop.ui.features.quotes.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuotesRepository {
    fun getQuotes(): Flow<List<Quote>>
    suspend fun addQuote(quote: List<Quote>)
}