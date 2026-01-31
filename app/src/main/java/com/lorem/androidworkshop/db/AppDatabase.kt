package com.lorem.androidworkshop.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lorem.androidworkshop.ui.features.quotes.data.dao.QuoteDao
import com.lorem.androidworkshop.ui.features.quotes.domain.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}
