package com.lexicards.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a single flashcard with spaced repetition metadata (SM-2 algorithm).
 *
 * SM-2 fields:
 * - easeFactor: how easy the card is (starts at 2.5)
 * - interval: days until next review
 * - repetitions: how many times reviewed successfully in a row
 * - nextReviewTime: epoch millis when this card is next due
 */
@Entity(tableName = "cards")
data class Card(
    @PrimaryKey val id: String = "",
    val deckId: String = "",
    val foreignWord: String = "",       // e.g. "Apple"
    val georgianWord: String = "",      // e.g. "ვაშლი"
    val foreignLanguage: String = "EN", // language code
    // SM-2 spaced repetition fields
    val easeFactor: Double = 2.5,
    val interval: Int = 1,              // days
    val repetitions: Int = 0,
    val nextReviewTime: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis()
)
