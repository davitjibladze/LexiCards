package com.lexicards.data.repository;

import com.lexicards.data.local.dao.CardDao;
import com.lexicards.data.local.dao.DeckDao;
import com.lexicards.data.remote.FirebaseDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class CardRepository_Factory implements Factory<CardRepository> {
  private final Provider<CardDao> cardDaoProvider;

  private final Provider<DeckDao> deckDaoProvider;

  private final Provider<FirebaseDataSource> remoteProvider;

  public CardRepository_Factory(Provider<CardDao> cardDaoProvider,
      Provider<DeckDao> deckDaoProvider, Provider<FirebaseDataSource> remoteProvider) {
    this.cardDaoProvider = cardDaoProvider;
    this.deckDaoProvider = deckDaoProvider;
    this.remoteProvider = remoteProvider;
  }

  @Override
  public CardRepository get() {
    return newInstance(cardDaoProvider.get(), deckDaoProvider.get(), remoteProvider.get());
  }

  public static CardRepository_Factory create(Provider<CardDao> cardDaoProvider,
      Provider<DeckDao> deckDaoProvider, Provider<FirebaseDataSource> remoteProvider) {
    return new CardRepository_Factory(cardDaoProvider, deckDaoProvider, remoteProvider);
  }

  public static CardRepository newInstance(CardDao cardDao, DeckDao deckDao,
      FirebaseDataSource remote) {
    return new CardRepository(cardDao, deckDao, remote);
  }
}
