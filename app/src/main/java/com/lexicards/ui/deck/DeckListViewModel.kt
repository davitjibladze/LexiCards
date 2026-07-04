package com.lexicards.ui.deck

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lexicards.data.repository.CardRepository
import com.lexicards.model.Deck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeckListViewModel @Inject constructor(
    private val repo: CardRepository
) : ViewModel() {

    val decks: StateFlow<List<Deck>> = repo.observeDecks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun createDeck(name: String, description: String, language: String) {
        viewModelScope.launch {
            repo.createDeck(name, description, language)
        }
    }

    fun deleteDeck(deck: Deck) {
        viewModelScope.launch {
            repo.deleteDeck(deck)
        }
    }

    fun syncFromRemote() {
        viewModelScope.launch {
            repo.syncFromRemote()
        }
    }
}
