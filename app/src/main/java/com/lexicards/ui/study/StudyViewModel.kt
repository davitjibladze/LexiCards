package com.lexicards.ui.study

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lexicards.data.repository.CardRepository
import com.lexicards.model.Card
import com.lexicards.model.Language
import com.lexicards.model.ReviewQuality
import com.lexicards.model.StudyMode
import com.lexicards.utils.SpacedRepetition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StudyUiState(
    val currentCard: Card? = null,
    val promptText: String = "",
    val isAnswerRevealed: Boolean = false,
    val answerFeedback: AnswerFeedback? = null,
    val sessionComplete: Boolean = false,
    val totalCards: Int = 0,
    val reviewedCount: Int = 0,
    val studyMode: StudyMode = StudyMode(Language.FOREIGN, Language.GEORGIAN)
)

data class AnswerFeedback(
    val result: SpacedRepetition.AnswerResult,
    val correctAnswer: String
)

@HiltViewModel
class StudyViewModel @Inject constructor(
    private val repo: CardRepository,
    savedState: SavedStateHandle
) : ViewModel() {

    private val deckId: String = savedState["deckId"] ?: ""

    private val _uiState = MutableStateFlow(StudyUiState())
    val uiState: StateFlow<StudyUiState> = _uiState.asStateFlow()

    private var queue: MutableList<Card> = mutableListOf()

    init {
        loadCards()
    }

    private fun loadCards() {
        viewModelScope.launch {
            repo.observeDueCards(deckId).first().let { cards ->
                // If no cards are due yet, take all cards (first session)
                queue = (cards.ifEmpty {
                    repo.observeCards(deckId).first()
                }).shuffled().toMutableList()

                _uiState.update {
                    it.copy(
                        totalCards = queue.size,
                        sessionComplete = queue.isEmpty()
                    )
                }
                advanceCard()
            }
        }
    }

    fun setStudyMode(promptLang: Language, answerLang: Language) {
        _uiState.update {
            it.copy(studyMode = StudyMode(promptLang, answerLang))
        }
        advanceCard()
    }

    private fun advanceCard() {
        if (queue.isEmpty()) {
            _uiState.update { it.copy(sessionComplete = true, currentCard = null) }
            return
        }
        val card = queue.first()
        val prompt = when (_uiState.value.studyMode.promptLanguage) {
            Language.FOREIGN -> card.foreignWord
            Language.GEORGIAN -> card.georgianWord
        }
        _uiState.update {
            it.copy(
                currentCard = card,
                promptText = prompt,
                isAnswerRevealed = false,
                answerFeedback = null
            )
        }
    }

    fun submitAnswer(userInput: String) {
        val state = _uiState.value
        val card = state.currentCard ?: return

        val correctAnswer = when (state.studyMode.answerLanguage) {
            Language.GEORGIAN -> card.georgianWord
            Language.FOREIGN -> card.foreignWord
        }

        val result = SpacedRepetition.checkAnswer(userInput, correctAnswer)

        val quality = when (result) {
            SpacedRepetition.AnswerResult.CORRECT -> ReviewQuality.CORRECT
            SpacedRepetition.AnswerResult.TYPO -> ReviewQuality.CORRECT_HARD
            SpacedRepetition.AnswerResult.WRONG -> ReviewQuality.BLACKOUT
        }

        val updatedCard = SpacedRepetition.calculateNextReview(card, quality)

        viewModelScope.launch {
            repo.updateCard(updatedCard)
        }

        queue.removeFirst()

        // If wrong, put it back later in the queue
        if (result == SpacedRepetition.AnswerResult.WRONG) {
            val insertAt = minOf(3, queue.size)
            queue.add(insertAt, updatedCard)
        }

        _uiState.update {
            it.copy(
                isAnswerRevealed = true,
                answerFeedback = AnswerFeedback(result, correctAnswer),
                reviewedCount = it.reviewedCount + 1
            )
        }
    }

    fun nextCard() {
        _uiState.update { it.copy(isAnswerRevealed = false, answerFeedback = null) }
        advanceCard()
    }
}
