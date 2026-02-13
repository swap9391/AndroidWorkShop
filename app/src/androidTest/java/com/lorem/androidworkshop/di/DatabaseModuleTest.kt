package com.lorem.androidworkshop.di

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lorem.androidworkshop.db.AppDatabase
import com.lorem.androidworkshop.ui.features.quotes.data.dao.QuoteDao
import com.lorem.androidworkshop.ui.features.quotes.data.repositoryimpl.QuoteRepositoryImpl
import com.lorem.androidworkshop.ui.features.quotes.domain.repository.QuotesRepository
import com.lorem.androidworkshop.ui.features.quotes.domain.usecaes.AddQuoteUseCase
import com.lorem.androidworkshop.ui.features.quotes.domain.usecaes.GetQuotesUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertSame
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DatabaseModuleTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject lateinit var db1: AppDatabase
    @Inject lateinit var db2: AppDatabase
    @Inject lateinit var dao: QuoteDao
    @Inject lateinit var repository: QuotesRepository
    @Inject lateinit var getQuotesUseCase: GetQuotesUseCase
    @Inject lateinit var addQuoteUseCase: AddQuoteUseCase

    @Before
    fun setup() {
        hiltRule.inject()
    }


    @Test
    fun `provideDatabase singleton instance verification`() {
        assertSame(db1, db2)
    }

    @Test
    fun `dao should not be null`() {
        assertNotNull(dao)
    }

    @Test
    fun `repository should be correctly bound`() {
        assertTrue(repository is QuoteRepositoryImpl)
    }

    @Test
    fun `usecases should be injected correctly`() {
        assertNotNull(getQuotesUseCase)
        assertNotNull(addQuoteUseCase)
    }

    @Test
    fun `full dependency graph integration test`() {
        assertNotNull(db1)
        assertNotNull(dao)
        assertNotNull(repository)
        assertNotNull(getQuotesUseCase)
        assertNotNull(addQuoteUseCase)
    }
}
