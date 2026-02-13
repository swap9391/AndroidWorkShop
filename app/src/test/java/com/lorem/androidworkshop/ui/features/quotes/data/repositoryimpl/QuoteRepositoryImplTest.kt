package com.lorem.androidworkshop.ui.features.quotes.data.repositoryimpl

import com.lorem.androidworkshop.ui.features.quotes.data.dao.QuoteDao
import com.lorem.androidworkshop.ui.features.quotes.data.entities.QuoteEntity
import com.lorem.androidworkshop.ui.features.quotes.domain.model.Quote
import com.lorem.androidworkshop.ui.features.quotes.domain.repository.QuotesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class QuoteRepositoryImplTest {
    private lateinit var repository: QuotesRepository
    private val dao: QuoteDao = Mockito.mock()


    @Before
    fun setup() {
        repository = QuoteRepositoryImpl(dao)
    }

    /**
     * Verifies that the repository correctly handles bulk insertion of quotes by partitioning
     * the input list into chunks of 100 before delegating the insert operation to the DAO.
     */
    @Test
    fun `addQuote should insert quotes in chunks of 100`() = runTest {

        val quotes = List(50) {
            Quote(it.toLong(), "Quote $it", "Author $it")
        }

        repository.addQuote(quotes)
        verify(dao, Mockito.times(1)).insertQuote(any())
    }

    @Test
    fun `addQuote should insert in multiple chunks if more than 100`() = runTest {
        val quotes = List(250) {
            Quote(it.toLong(), "Quote $it", "Author $it")
        }

        repository.addQuote(quotes)

        //verify() checks whether a function was called on a mock object.
        //times(3) “This function must be called exactly 3 times.”
        //insertQuote(any())
        // insertQuote() → the function we expect to be called
        // any() → we don’t care about the argument value
        verify(dao, Mockito.times(3)).insertQuote(any())
    }


    @Test
    fun `getQuotes should return a list of quotes`() = runTest {
        val fakeUsers = listOf(
            QuoteEntity(1, "Quote 1", "Author 1"),
            QuoteEntity(2, "Quote 2", "Author 2")
        )

        whenever(dao.getAllQuotes()).thenReturn(flowOf(fakeUsers))

        val result = repository.getQuotes().first()

        assertEquals("Quote 1", result[0].quoteText)
    }



}