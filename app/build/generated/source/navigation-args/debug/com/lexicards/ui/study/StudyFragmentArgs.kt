package com.lexicards.ui.study

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class StudyFragmentArgs(
  public val deckId: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("deckId", this.deckId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("deckId", this.deckId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): StudyFragmentArgs {
      bundle.setClassLoader(StudyFragmentArgs::class.java.classLoader)
      val __deckId : String?
      if (bundle.containsKey("deckId")) {
        __deckId = bundle.getString("deckId")
        if (__deckId == null) {
          throw IllegalArgumentException("Argument \"deckId\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"deckId\" is missing and does not have an android:defaultValue")
      }
      return StudyFragmentArgs(__deckId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): StudyFragmentArgs {
      val __deckId : String?
      if (savedStateHandle.contains("deckId")) {
        __deckId = savedStateHandle["deckId"]
        if (__deckId == null) {
          throw IllegalArgumentException("Argument \"deckId\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"deckId\" is missing and does not have an android:defaultValue")
      }
      return StudyFragmentArgs(__deckId)
    }
  }
}
