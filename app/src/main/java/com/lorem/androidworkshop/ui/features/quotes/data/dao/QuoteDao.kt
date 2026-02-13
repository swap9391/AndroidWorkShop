package com.lorem.androidworkshop.ui.features.quotes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lorem.androidworkshop.ui.features.quotes.data.entities.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quotes")
    fun getAllQuotes(): Flow<List<QuoteEntity>>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertQuote(quote: List<QuoteEntity>)

    @Delete
    suspend fun deleteQuote(quote: QuoteEntity)
}