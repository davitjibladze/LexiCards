package com.lexicards.ui.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lexicards.data.local.dao.CardDao
import com.lexicards.data.local.dao.DeckDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class StatsUiState(
    val totalDecks: Int = 0,
    val totalCards: Int = 0,
    val dueCards: Int = 0,
    val masteredCards: Int = 0   // interval >= 21 days
)

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val deckDao: DeckDao,
    private val cardDao: CardDao
) : ViewModel() {

    val stats: StateFlow<StatsUiState> = combine(
        deckDao.getAllDecks(),
        cardDao.getAllCards()
    ) { decks, cards ->
        val now = System.currentTimeMillis()
        StatsUiState(
            totalDecks = decks.size,
            totalCards = cards.size,
            dueCards = cards.count { it.nextReviewTime <= now },
            masteredCards = cards.count { it.interval >= 21 }
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), StatsUiState())
}
