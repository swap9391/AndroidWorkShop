package com.lorem.androidworkshop.di

import android.content.Context
import androidx.room.Room
import com.lorem.androidworkshop.db.AppDatabase
import com.lorem.androidworkshop.ui.features.quotes.data.dao.QuoteDao
import com.lorem.androidworkshop.ui.features.quotes.data.repositoryimpl.QuoteRepositoryImpl
import com.lorem.androidworkshop.ui.features.quotes.domain.repository.QuotesRepository
import com.lorem.androidworkshop.ui.features.quotes.domain.usecaes.AddQuoteUseCase
import com.lorem.androidworkshop.ui.features.quotes.domain.usecaes.GetQuotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object TestDatabaseModule {

    @Provides
    @Singleton
    fun provideInMemoryDb(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

    @Provides
    fun provideQuoteDao(db: AppDatabase): QuoteDao =
        db.quoteDao()

    @Provides
    fun provideQuoteRepository(
        dao: QuoteDao
    ): QuotesRepository =
        QuoteRepositoryImpl(dao)

    @Provides
    fun provideGetQuotesUseCase(
        repository: QuotesRepository
    ): GetQuotesUseCase =
        GetQuotesUseCase(repository)

    @Provides
    fun provideAddQuoteUseCase(
        repository: QuotesRepository
    ): AddQuoteUseCase =
        AddQuoteUseCase(repository)
}
