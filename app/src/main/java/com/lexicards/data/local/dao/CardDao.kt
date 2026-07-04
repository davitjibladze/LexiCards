package com.lexicards.data.local.dao

import androidx.room.*
import com.lexicards.model.Card
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Query("SELECT * FROM cards ORDER BY nextReviewTime ASC")
    fun getAllCards(): Flow<List<Card>>

    @Query("SELECT * FROM cards WHERE deckId = :deckId ORDER BY nextReviewTime ASC")
    fun getCardsByDeck(deckId: String): Flow<List<Card>>

    @Query("SELECT * FROM cards WHERE deckId = :deckId AND nextReviewTime <= :now ORDER BY nextReviewTime ASC")
    fun getDueCards(deckId: String, now: Long = System.currentTimeMillis()): Flow<List<Card>>

    @Query("SELECT COUNT(*) FROM cards WHERE deckId = :deckId")
    suspend fun getCardCount(deckId: String): Int

    @Query("SELECT * FROM cards WHERE id = :id")
    suspend fun getCardById(id: String): Card?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: Card)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cards: List<Card>)

    @Update
    suspend fun update(card: Card)

    @Delete
    suspend fun delete(card: Card)

    @Query("DELETE FROM cards WHERE deckId = :deckId")
    suspend fun deleteAllInDeck(deckId: String)
}
