package com.lorem.myprepproj.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lorem.myprepproj.model.Quote

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteListScreen(
    quotes: List<Quote>,
    onQuoteClick: (Quote) -> Unit
) {
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
                text = quote.text,
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

@Preview
@Composable
private fun QuoteListPrev() {
    QuoteListScreen(quotes = listOf(
        Quote("Quote 1", "Author 1"),
        Quote("Quote 2", "Author 2"),
        Quote("Quote 3", "Author 3"),
        Quote("Quote 4", "Author 4"),
        Quote("Quote 5", "Author 5"),
        Quote("Quote 6", "Author 6"),
        Quote("Quote 7", "Author 7"),
        Quote("Quote 8", "Author 8"),
        Quote("Quote 9", "Author 9"),
        Quote("Quote 10", "Author 10")
    ) , onQuoteClick = {})
}
