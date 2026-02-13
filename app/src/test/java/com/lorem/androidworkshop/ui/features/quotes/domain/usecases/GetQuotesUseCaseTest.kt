package com.lorem.androidworkshop.ui.features.quotes.domain.usecases

import com.lorem.androidworkshop.ui.features.quotes.domain.model.Quote
import com.lorem.androidworkshop.ui.features.quotes.domain.repository.QuotesRepository
import com.lorem.androidworkshop.ui.features.quotes.domain.usecaes.GetQuotesUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals


class GetQuotesUseCaseTest {

    private val quotesRepository = mock<QuotesRepository>()
    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun setup(){
        getQuotesUseCase = GetQuotesUseCase(quotesRepository)
    }

    @Test
    fun `test getQuotesUseCase`() = runTest {

        val fakeQuotes = listOf(
            Quote(
                id = 1,
                quoteText = "Quote 1",
                author = "Author 1",
            ),
            Quote(
                id = 2,
                quoteText = "Quote 2",
                author = "Author 2")
        )

       whenever(quotesRepository.getQuotes()).thenReturn(flowOf(fakeQuotes))
       val result = getQuotesUseCase().first()

        assertEquals(fakeQuotes,result)
        verify(quotesRepository).getQuotes()
    }

}