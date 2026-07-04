package com.lexicards.data.repository

import com.lexicards.data.local.dao.CardDao
import com.lexicards.data.local.dao.DeckDao
import com.lexicards.data.remote.FirebaseDataSource
import com.lexicards.model.Card
import com.lexicards.model.Deck
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardRepository @Inject constructor(
    private val cardDao: CardDao,
    private val deckDao: DeckDao,
    private val remote: FirebaseDataSource
) {

    // ── Decks ──────────────────────────────────────────────────────────────

    fun observeDecks(): Flow<List<Deck>> = deckDao.getAllDecks()

    suspend fun createDeck(name: String, description: String, language: String): Deck {
        val deck = Deck(
            id = UUID.randomUUID().toString(),
            name = name,
            description = description,
            foreignLanguage = language
        )
        deckDao.insert(deck)
        remote.saveDeck(deck)
        return deck
    }

    suspend fun deleteDeck(deck: Deck) {
        deckDao.delete(deck)
        cardDao.deleteAllInDeck(deck.id)
        remote.deleteDeck(deck.id)
        remote.deleteDeckCards(deck.id)
    }

    suspend fun updateDeckCardCount(deckId: String) {
        val count = cardDao.getCardCount(deckId)
        val deck = deckDao.getDeckById(deckId) ?: return
        val updated = deck.copy(cardCount = count)
        deckDao.update(updated)
        remote.saveDeck(updated)
    }

    // ── Cards ──────────────────────────────────────────────────────────────

    fun observeCards(deckId: String): Flow<List<Card>> = cardDao.getCardsByDeck(deckId)

    fun observeDueCards(deckId: String): Flow<List<Card>> = cardDao.getDueCards(deckId)

    suspend fun addCard(deckId: String, foreign: String, georgian: String, lang: String): Card {
        val card = Card(
            id = UUID.randomUUID().toString(),
            deckId = deckId,
            foreignWord = foreign,
            georgianWord = georgian,
            foreignLanguage = lang
        )
        cardDao.insert(card)
        remote.saveCard(card)
        updateDeckCardCount(deckId)
        return card
    }

    suspend fun updateCard(card: Card) {
        cardDao.update(card)
        remote.updateCard(card)
    }

    suspend fun deleteCard(card: Card) {
        cardDao.delete(card)
        remote.deleteCard(card)
        updateDeckCardCount(card.deckId)
    }

    /** Sync from Firebase to local Room (e.g. on first launch) */
    suspend fun syncFromRemote() {
        val decks = remote.fetchDecks()
        decks.forEach { deck ->
            deckDao.insert(deck)
            val cards = remote.fetchCards(deck.id)
            cardDao.insertAll(cards)
        }
    }
}
