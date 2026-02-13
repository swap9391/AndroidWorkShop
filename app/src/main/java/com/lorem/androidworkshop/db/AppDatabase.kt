package com.lorem.androidworkshop.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lorem.androidworkshop.ui.features.quotes.data.dao.QuoteDao
import com.lorem.androidworkshop.ui.features.quotes.data.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}
