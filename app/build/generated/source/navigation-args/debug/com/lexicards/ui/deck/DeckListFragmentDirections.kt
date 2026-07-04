package com.lexicards.ui.deck

import android.os.Bundle
import androidx.navigation.NavDirections
import com.lexicards.R
import kotlin.Int
import kotlin.String

public class DeckListFragmentDirections private constructor() {
  private data class ActionDeckListToCardList(
    public val deckId: String,
    public val deckName: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.actionDeckListToCardList

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("deckId", this.deckId)
        result.putString("deckName", this.deckName)
        return result
      }
  }

  public companion object {
    public fun actionDeckListToCardList(deckId: String, deckName: String): NavDirections =
        ActionDeckListToCardList(deckId, deckName)
  }
}
