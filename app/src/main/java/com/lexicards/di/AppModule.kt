package com.lexicards.di

import android.content.Context
import androidx.room.Room
import com.lexicards.data.local.LexiDatabase
import com.lexicards.data.local.dao.CardDao
import com.lexicards.data.local.dao.DeckDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): LexiDatabase =
        Room.databaseBuilder(ctx, LexiDatabase::class.java, "lexi_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideCardDao(db: LexiDatabase): CardDao = db.cardDao()

    @Provides
    fun provideDeckDao(db: LexiDatabase): DeckDao = db.deckDao()
}
