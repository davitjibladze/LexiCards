package com.lexicards.ui.cards

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lexicards.data.repository.CardRepository
import com.lexicards.model.Card
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    private val repo: CardRepository,
    savedState: SavedStateHandle
) : ViewModel() {

    val deckId: String = savedState["deckId"] ?: ""

    val cards: StateFlow<List<Card>> = repo.observeCards(deckId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addCard(foreign: String, georgian: String, lang: String) {
        viewModelScope.launch {
            repo.addCard(deckId, foreign, georgian, lang)
        }
    }

    fun deleteCard(card: Card) {
        viewModelScope.launch {
            repo.deleteCard(card)
        }
    }
}
