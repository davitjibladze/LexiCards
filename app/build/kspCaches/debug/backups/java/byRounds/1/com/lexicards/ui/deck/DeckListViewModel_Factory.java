package com.lexicards.ui.deck;

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
public final class DeckListViewModel_Factory implements Factory<DeckListViewModel> {
  private final Provider<CardRepository> repoProvider;

  public DeckListViewModel_Factory(Provider<CardRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public DeckListViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static DeckListViewModel_Factory create(Provider<CardRepository> repoProvider) {
    return new DeckListViewModel_Factory(repoProvider);
  }

  public static DeckListViewModel newInstance(CardRepository repo) {
    return new DeckListViewModel(repo);
  }
}
