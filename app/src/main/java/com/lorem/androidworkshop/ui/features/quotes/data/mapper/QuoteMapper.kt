package com.lorem.androidworkshop.ui.features.quotes.data.mapper

import com.lorem.androidworkshop.ui.features.quotes.data.entities.QuoteEntity
import com.lorem.androidworkshop.ui.features.quotes.domain.model.Quote

fun QuoteEntity.toDomain() = Quote(id, quoteText , author)

fun Quote.toEntity() = QuoteEntity(id, quoteText, author)