package com.lorem.androidworkshop.ui.features.quotes.domain.usecaes

import com.lorem.androidworkshop.ui.features.quotes.domain.model.Quote
import com.lorem.androidworkshop.ui.features.quotes.domain.repository.QuotesRepository
import kotlinx.coroutines.flow.Flow

class GetQuotesUseCase(private val quotesRepository: QuotesRepository) {
     operator fun invoke(): Flow<List<Quote>> = quotesRepository.getQuotes()
}