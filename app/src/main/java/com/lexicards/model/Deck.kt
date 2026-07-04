package com.lexicards.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "decks")
data class Deck(
    @PrimaryKey val id: String = "",
    val name: String = "",
    val description: String = "",
    val foreignLanguage: String = "EN",
    val cardCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
