package com.lexicards.utils

import com.lexicards.model.Card
import com.lexicards.model.ReviewQuality
import kotlin.math.max

/**
 * Implementation of the SM-2 (SuperMemo 2) spaced repetition algorithm.
 *
 * This is the core "new functionality" of LexiCards.
 * Cards with lower quality scores get shorter intervals and appear sooner.
 *
 * Algorithm:
 *  - If quality < 3: reset repetitions, set interval to 1
 *  - If quality >= 3:
 *      - repetitions == 0: interval = 1
 *      - repetitions == 1: interval = 6
 *      - repetitions > 1:  interval = round(prev_interval * easeFactor)
 *  - EF' = EF + (0.1 - (5-q) * (0.08 + (5-q)*0.02))
 *  - EF minimum is 1.3
 */
object SpacedRepetition {

    private const val MIN_EASE_FACTOR = 1.3
    private const val MS_IN_DAY = 86_400_000L

    fun calculateNextReview(card: Card, quality: ReviewQuality): Card {
        val q = quality.value
        val newEaseFactor = max(
            MIN_EASE_FACTOR,
            card.easeFactor + (0.1 - (5 - q) * (0.08 + (5 - q) * 0.02))
        )

        return if (q < 3) {
            // Forgot — reset
            card.copy(
                repetitions = 0,
                interval = 1,
                easeFactor = newEaseFactor,
                nextReviewTime = System.currentTimeMillis() + MS_IN_DAY
            )
        } else {
            val newInterval = when (card.repetitions) {
                0 -> 1
                1 -> 6
                else -> (card.interval * card.easeFactor).toInt()
            }
            val newRepetitions = card.repetitions + 1
            card.copy(
                repetitions = newRepetitions,
                interval = newInterval,
                easeFactor = newEaseFactor,
                nextReviewTime = System.currentTimeMillis() + newInterval * MS_IN_DAY
            )
        }
    }

    /** Cards due for review right now */
    fun isDue(card: Card): Boolean =
        System.currentTimeMillis() >= card.nextReviewTime

    /**
     * Fuzzy text comparison — tolerates minor spelling mistakes.
     * Returns true if the user's answer is close enough.
     */
    fun checkAnswer(userInput: String, correct: String): AnswerResult {
        val input = userInput.trim().lowercase()
        val expected = correct.trim().lowercase()

        return when {
            input == expected -> AnswerResult.CORRECT
            levenshtein(input, expected) <= 1 -> AnswerResult.TYPO
            else -> AnswerResult.WRONG
        }
    }

    private fun levenshtein(a: String, b: String): Int {
        val dp = Array(a.length + 1) { IntArray(b.length + 1) }
        for (i in 0..a.length) dp[i][0] = i
        for (j in 0..b.length) dp[0][j] = j
        for (i in 1..a.length) {
            for (j in 1..b.length) {
                dp[i][j] = if (a[i - 1] == b[j - 1]) dp[i - 1][j - 1]
                else 1 + minOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
            }
        }
        return dp[a.length][b.length]
    }

    enum class AnswerResult { CORRECT, TYPO, WRONG }
}
