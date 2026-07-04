package com.lexicards.ui.cards;

import androidx.lifecycle.SavedStateHandle;
import com.lexicards.data.repository.CardRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class CardListViewModel_Factory implements Factory<CardListViewModel> {
  private final Provider<CardRepository> repoProvider;

  private final Provider<SavedStateHandle> savedStateProvider;

  public CardListViewModel_Factory(Provider<CardRepository> repoProvider,
      Provider<SavedStateHandle> savedStateProvider) {
    this.repoProvider = repoProvider;
    this.savedStateProvider = savedStateProvider;
  }

  @Override
  public CardListViewModel get() {
    return newInstance(repoProvider.get(), savedStateProvider.get());
  }

  public static CardListViewModel_Factory create(Provider<CardRepository> repoProvider,
      Provider<SavedStateHandle> savedStateProvider) {
    return new CardListViewModel_Factory(repoProvider, savedStateProvider);
  }

  public static CardListViewModel newInstance(CardRepository repo, SavedStateHandle savedState) {
    return new CardListViewModel(repo, savedState);
  }
}
