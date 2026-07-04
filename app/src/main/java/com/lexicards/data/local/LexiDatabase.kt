package com.lexicards.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lexicards.data.local.dao.CardDao
import com.lexicards.data.local.dao.DeckDao
import com.lexicards.model.Card
import com.lexicards.model.Deck

@Database(
    entities = [Card::class, Deck::class],
    version = 1,
    exportSchema = false
)
abstract class LexiDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun deckDao(): DeckDao
}
