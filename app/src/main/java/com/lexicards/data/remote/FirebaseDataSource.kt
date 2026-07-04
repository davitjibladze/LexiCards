package com.lexicards.data.remote

import com.google.firebase.database.FirebaseDatabase
import com.lexicards.model.Card
import com.lexicards.model.Deck
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseDataSource @Inject constructor() {

    private val db = FirebaseDatabase.getInstance(
        "https://lexicards-a0aab-default-rtdb.europe-west1.firebasedatabase.app"
    ).reference

    suspend fun saveDeck(deck: Deck) {
        db.child("decks").child(deck.id).setValue(deck).await()
    }

    suspend fun fetchDecks(): List<Deck> {
        val snapshot = db.child("decks").get().await()
        return snapshot.children.mapNotNull { it.getValue(Deck::class.java) }
    }

    suspend fun deleteDeck(deckId: String) {
        db.child("decks").child(deckId).removeValue().await()
    }

    suspend fun saveCard(card: Card) {
        db.child("cards").child(card.deckId).child(card.id).setValue(card).await()
    }

    suspend fun fetchCards(deckId: String): List<Card> {
        val snapshot = db.child("cards").child(deckId).get().await()
        return snapshot.children.mapNotNull { it.getValue(Card::class.java) }
    }

    suspend fun updateCard(card: Card) {
        db.child("cards").child(card.deckId).child(card.id).setValue(card).await()
    }

    suspend fun deleteCard(card: Card) {
        db.child("cards").child(card.deckId).child(card.id).removeValue().await()
    }

    suspend fun deleteDeckCards(deckId: String) {
        db.child("cards").child(deckId).removeValue().await()
    }
}
