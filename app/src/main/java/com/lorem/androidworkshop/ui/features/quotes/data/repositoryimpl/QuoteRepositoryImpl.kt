package com.lorem.androidworkshop.ui.features.quotes.data.repositoryimpl

import com.lorem.androidworkshop.ui.features.quotes.data.dao.QuoteDao
import com.lorem.androidworkshop.ui.features.quotes.data.mapper.toDomain
import com.lorem.androidworkshop.ui.features.quotes.data.mapper.toEntity
import com.lorem.androidworkshop.ui.features.quotes.domain.model.Quote
import com.lorem.androidworkshop.ui.features.quotes.domain.repository.QuotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuoteRepositoryImpl(private val dao: QuoteDao) : QuotesRepository {
    override fun getQuotes(): Flow<List<Quote>> =
        dao.getAllQuotes().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun addQuote(quote: List<Quote>) {
     val entities = quote.map { it.toEntity() }
        entities.chunked(100).forEach { chunk ->
            dao.insertQuote(chunk)
        }
    }
}