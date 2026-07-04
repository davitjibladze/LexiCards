package com.lexicards.ui.stats;

import com.lexicards.data.local.dao.CardDao;
import com.lexicards.data.local.dao.DeckDao;
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
public final class StatsViewModel_Factory implements Factory<StatsViewModel> {
  private final Provider<DeckDao> deckDaoProvider;

  private final Provider<CardDao> cardDaoProvider;

  public StatsViewModel_Factory(Provider<DeckDao> deckDaoProvider,
      Provider<CardDao> cardDaoProvider) {
    this.deckDaoProvider = deckDaoProvider;
    this.cardDaoProvider = cardDaoProvider;
  }

  @Override
  public StatsViewModel get() {
    return newInstance(deckDaoProvider.get(), cardDaoProvider.get());
  }

  public static StatsViewModel_Factory create(Provider<DeckDao> deckDaoProvider,
      Provider<CardDao> cardDaoProvider) {
    return new StatsViewModel_Factory(deckDaoProvider, cardDaoProvider);
  }

  public static StatsViewModel newInstance(DeckDao deckDao, CardDao cardDao) {
    return new StatsViewModel(deckDao, cardDao);
  }
}
