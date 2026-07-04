package com.lexicards.ui.cards

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class CardListFragmentArgs(
  public val deckId: String,
  public val deckName: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("deckId", this.deckId)
    result.putString("deckName", this.deckName)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("deckId", this.deckId)
    result.set("deckName", this.deckName)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): CardListFragmentArgs {
      bundle.setClassLoader(CardListFragmentArgs::class.java.classLoader)
      val __deckId : String?
      if (bundle.containsKey("deckId")) {
        __deckId = bundle.getString("deckId")
        if (__deckId == null) {
          throw IllegalArgumentException("Argument \"deckId\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"deckId\" is missing and does not have an android:defaultValue")
      }
      val __deckName : String?
      if (bundle.containsKey("deckName")) {
        __deckName = bundle.getString("deckName")
        if (__deckName == null) {
          throw IllegalArgumentException("Argument \"deckName\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"deckName\" is missing and does not have an android:defaultValue")
      }
      return CardListFragmentArgs(__deckId, __deckName)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): CardListFragmentArgs {
      val __deckId : String?
      if (savedStateHandle.contains("deckId")) {
        __deckId = savedStateHandle["deckId"]
        if (__deckId == null) {
          throw IllegalArgumentException("Argument \"deckId\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"deckId\" is missing and does not have an android:defaultValue")
      }
      val __deckName : String?
      if (savedStateHandle.contains("deckName")) {
        __deckName = savedStateHandle["deckName"]
        if (__deckName == null) {
          throw IllegalArgumentException("Argument \"deckName\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"deckName\" is missing and does not have an android:defaultValue")
      }
      return CardListFragmentArgs(__deckId, __deckName)
    }
  }
}
