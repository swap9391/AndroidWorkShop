package com.lorem.androidworkshop.ui.features.quotes.domain.usecases

import com.lorem.androidworkshop.ui.features.quotes.domain.model.Quote
import com.lorem.androidworkshop.ui.features.quotes.domain.repository.QuotesRepository
import com.lorem.androidworkshop.ui.features.quotes.domain.usecaes.AddQuoteUseCase
import com.lorem.androidworkshop.ui.features.quotes.domain.usecaes.GetQuotesUseCase
import com.lorem.androidworkshop.ui.features.quotes.presentation.screens.QuoteItem
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test

class AddQuotesUseCaseTest {

    private val quotesRepository = mock<QuotesRepository>()
    private lateinit var addQuotesUseCase : AddQuoteUseCase

    @Before
    fun setup(){
        addQuotesUseCase = AddQuoteUseCase(quotesRepository)
    }

    @Test
    fun `test addQuotesUseCase`() = runTest{

        val fakeQuotes = listOf(
            Quote(
                id = 1,
                quoteText = "Quote 1",
                author = "Author 1",
            )
        )
        verify(quotesRepository).addQuote(fakeQuotes)
    }


}