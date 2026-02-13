package com.lorem.androidworkshop.ui.features.quotes.data.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lorem.androidworkshop.db.AppDatabase
import com.lorem.androidworkshop.ui.features.quotes.data.entities.QuoteEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuoteDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var quoteDao: QuoteDao


    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        quoteDao = database.quoteDao()
    }

    @After
    fun tearDown() {
        database.close()
    }


    @Test
    fun `insertQuote single item insertion`() = runTest {
        val entity = QuoteEntity(1, "Hello", "Author")

        quoteDao.insertQuote(listOf(entity))

        val result = quoteDao.getAllQuotes().first()

        assertEquals(1, result.size)
        assertEquals("Hello", result[0].quoteText)
    }


    @Test
    fun `getAllQuotes empty table emission`() = runTest {
        // Verify that the Flow emits an empty list when the quotes table is initially empty.
        val result = quoteDao.getAllQuotes().first()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `getAllQuotes data retrieval`() = runTest {
        // Verify that the Flow emits the correct list of QuoteEntity objects matching the database content.
        val entities = listOf(
            QuoteEntity(1, "Q1", "A1"),
            QuoteEntity(2, "Q2", "A2")
        )

        quoteDao.insertQuote(entities)

        val result = quoteDao.getAllQuotes().first()

        assertEquals(2, result.size)
        assertEquals("Q1", result[0].quoteText)
    }

    @Test
    fun `getAllQuotes real time update observation`() = runTest {
        // Verify that the Flow emits a new list automatically when a new QuoteEntity is inserted into 
        // the database.
        val first = QuoteEntity(1, "Q1", "A1")
        val second = QuoteEntity(2, "Q2", "A2")

        val flow = quoteDao.getAllQuotes()

        // Initial empty emission
        assertTrue(flow.first().isEmpty())

        quoteDao.insertQuote(listOf(first))
        assertEquals(1, flow.first().size)

        quoteDao.insertQuote(listOf(second))
        assertEquals(2, flow.first().size)
    }



    @Test
    fun `insertQuote multiple items insertion`() = runTest {
        // Verify that a list containing multiple QuoteEntity objects is correctly persisted in a 
        // single transaction.
        val entities = listOf(
            QuoteEntity(1, "Q1", "A1"),
            QuoteEntity(2, "Q2", "A2"),
            QuoteEntity(3, "Q3", "A3")
        )

        quoteDao.insertQuote(entities)

        val result = quoteDao.getAllQuotes().first()

        assertEquals(3, result.size)
    }


    @Test
    fun `insertQuote empty list handling`() = runTest {
        // Verify that passing an empty list to insertQuote does not trigger any database errors 
        // or changes.
        quoteDao.insertQuote(emptyList())

        val result = quoteDao.getAllQuotes().first()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `deleteQuote existing record removal`() = runTest {
        // Verify that a specific QuoteEntity is successfully removed from the database based 
        // on its primary key.
        val entity = QuoteEntity(1, "Q1", "A1")

        quoteDao.insertQuote(listOf(entity))
        quoteDao.deleteQuote(entity)

        val result = quoteDao.getAllQuotes().first()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `deleteQuote non existent record handling`() = runTest{
        // Verify that attempting to delete a QuoteEntity that does not exist in the database 
        // results in no changes and no exceptions.
        val entity = QuoteEntity(99, "Q", "A")

        quoteDao.deleteQuote(entity)

        val result = quoteDao.getAllQuotes().first()

        assertTrue(result.isEmpty())
    }

}