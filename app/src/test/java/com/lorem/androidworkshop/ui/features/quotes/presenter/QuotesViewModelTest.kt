package com.lorem.androidworkshop.ui.features.quotes.presenter

import com.lorem.androidworkshop.MainDispatcherRule
import com.lorem.androidworkshop.ui.features.quotes.domain.model.Quote
import com.lorem.androidworkshop.ui.features.quotes.domain.usecaes.AddQuoteUseCase
import com.lorem.androidworkshop.ui.features.quotes.domain.usecaes.GetQuotesUseCase
import com.lorem.androidworkshop.ui.features.quotes.presentation.viewmodel.QuotesViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class QuotesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: QuotesViewModel

    private val getQuotesUseCase = mock<GetQuotesUseCase>()
    private val addQuoteUseCase = mock<AddQuoteUseCase>()

    @Before
    fun setup(){
        viewModel = QuotesViewModel(getQuotesUseCase, addQuoteUseCase)
    }

    @Test
    fun `quotes should emit data from usecase`() = runTest {
        val fakeQuotes = listOf(
            Quote(1, "Hello", "Author")
        )

        whenever(getQuotesUseCase.invoke())
            .thenReturn(flowOf(fakeQuotes))

        val result = viewModel.quotesData.first()

        assertEquals(fakeQuotes, result)
    }


}