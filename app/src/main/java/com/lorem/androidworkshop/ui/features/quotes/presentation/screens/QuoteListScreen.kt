package com.lorem.androidworkshop.ui.features.quotes.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lorem.androidworkshop.ui.features.quotes.domain.model.Quote
import com.lorem.androidworkshop.ui.features.quotes.presentation.viewmodel.QuotesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteListScreen(
    //quotes: List<Quote>,
    onQuoteClick: (Quote) -> Unit,
    viewModel: QuotesViewModel = hiltViewModel()
) {

    val quotes by viewModel.quotesData.collectAsState()

    LaunchedEffect(Unit) {
            viewModel.addQuote()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Quotes") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(quotes) { quote ->
                QuoteItem(quote = quote, onClick = { onQuoteClick(quote) })
            }
        }
    }
}

@Composable
fun QuoteItem(quote: Quote, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = quote.quoteText,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "- ${quote.author}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp

            )
        }
    }
}

/*@Preview
@Composable
private fun QuoteListPrev() {
    QuoteListScreen(quotes = listOf(
        Quote(1,"Quote 1", "Author 1"),
        Quote(2,"Quote 2", "Author 2"),
        Quote(3,"Quote 3", "Author 3"),
        Quote(4,"Quote 4", "Author 4"),
        Quote(5,"Quote 5", "Author 5"),
        Quote(6,"Quote 6", "Author 6"),
        Quote(7,"Quote 7", "Author 7"),
        Quote(8,"Quote 8", "Author 8"),
        Quote(9,"Quote 9", "Author 9"),
        Quote(10,"Quote 10", "Author 10")
    ) , onQuoteClick = {})
}*/
