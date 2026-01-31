package com.lorem.androidworkshop.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lorem.androidworkshop.db.AppDatabase
import com.lorem.androidworkshop.ui.features.quotes.data.dao.QuoteDao
import com.lorem.androidworkshop.ui.features.quotes.data.repositoryimpl.QuoteRepositoryImpl
import com.lorem.androidworkshop.ui.features.quotes.domain.repository.QuotesRepository
import com.lorem.androidworkshop.ui.features.quotes.domain.usecaes.AddQuoteUseCase
import com.lorem.androidworkshop.ui.features.quotes.domain.usecaes.GetQuotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "my_database"
        ).setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING).build()

    @Provides
    fun provideQuoteDao(database: AppDatabase) = database.quoteDao()

    @Provides
    fun provideQuoteRepository(quoteDao: QuoteDao): QuotesRepository = QuoteRepositoryImpl(quoteDao)

    @Provides
    fun provideGetQuotesUseCase(quotesRepository: QuotesRepository) =
        GetQuotesUseCase(quotesRepository)

    @Provides
    fun provideAddQuoteUseCase(quotesRepository: QuotesRepository) =
        AddQuoteUseCase(quotesRepository)

}