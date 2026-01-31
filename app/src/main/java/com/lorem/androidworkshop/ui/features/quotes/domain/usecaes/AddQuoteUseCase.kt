package com.lorem.androidworkshop.ui.features.quotes.domain.usecaes

import com.lorem.androidworkshop.ui.features.quotes.domain.model.Quote
import com.lorem.androidworkshop.ui.features.quotes.domain.repository.QuotesRepository

class AddQuoteUseCase(private val quotesRepository: QuotesRepository) {
    suspend operator fun invoke(quote: List<Quote>) = quotesRepository.addQuote(quote)

}