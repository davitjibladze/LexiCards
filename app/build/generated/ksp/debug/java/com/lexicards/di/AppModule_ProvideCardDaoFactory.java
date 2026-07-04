package com.lexicards.di;

import com.lexicards.data.local.LexiDatabase;
import com.lexicards.data.local.dao.CardDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideCardDaoFactory implements Factory<CardDao> {
  private final Provider<LexiDatabase> dbProvider;

  public AppModule_ProvideCardDaoFactory(Provider<LexiDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public CardDao get() {
    return provideCardDao(dbProvider.get());
  }

  public static AppModule_ProvideCardDaoFactory create(Provider<LexiDatabase> dbProvider) {
    return new AppModule_ProvideCardDaoFactory(dbProvider);
  }

  public static CardDao provideCardDao(LexiDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideCardDao(db));
  }
}
