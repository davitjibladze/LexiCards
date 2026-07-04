package com.lexicards.di;

import com.lexicards.data.local.LexiDatabase;
import com.lexicards.data.local.dao.DeckDao;
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
public final class AppModule_ProvideDeckDaoFactory implements Factory<DeckDao> {
  private final Provider<LexiDatabase> dbProvider;

  public AppModule_ProvideDeckDaoFactory(Provider<LexiDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public DeckDao get() {
    return provideDeckDao(dbProvider.get());
  }

  public static AppModule_ProvideDeckDaoFactory create(Provider<LexiDatabase> dbProvider) {
    return new AppModule_ProvideDeckDaoFactory(dbProvider);
  }

  public static DeckDao provideDeckDao(LexiDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideDeckDao(db));
  }
}
