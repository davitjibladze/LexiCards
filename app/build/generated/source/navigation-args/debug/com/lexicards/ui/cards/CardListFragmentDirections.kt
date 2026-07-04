package com.lexicards.ui.cards

import android.os.Bundle
import androidx.navigation.NavDirections
import com.lexicards.R
import kotlin.Int
import kotlin.String

public class CardListFragmentDirections private constructor() {
  private data class ActionCardListToStudy(
    public val deckId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.actionCardListToStudy

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("deckId", this.deckId)
        return result
      }
  }

  public companion object {
    public fun actionCardListToStudy(deckId: String): NavDirections = ActionCardListToStudy(deckId)
  }
}
