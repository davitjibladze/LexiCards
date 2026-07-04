package com.lexicards.model

/**
 * Quality rating for SM-2 algorithm (0-5).
 * 0 = complete blackout, 5 = perfect response with no hesitation.
 */
enum class ReviewQuality(val value: Int) {
    BLACKOUT(0),
    WRONG(1),
    WRONG_EASY(2),
    CORRECT_HARD(3),
    CORRECT(4),
    PERFECT(5)
}

data class StudyMode(
    val promptLanguage: Language,  // language shown to user
    val answerLanguage: Language   // language user must type
)

enum class Language { FOREIGN, GEORGIAN }
