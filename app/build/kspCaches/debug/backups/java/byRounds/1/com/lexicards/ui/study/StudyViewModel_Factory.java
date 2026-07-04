package com.lexicards.ui.study;

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
public final class StudyViewModel_Factory implements Factory<StudyViewModel> {
  private final Provider<CardRepository> repoProvider;

  private final Provider<SavedStateHandle> savedStateProvider;

  public StudyViewModel_Factory(Provider<CardRepository> repoProvider,
      Provider<SavedStateHandle> savedStateProvider) {
    this.repoProvider = repoProvider;
    this.savedStateProvider = savedStateProvider;
  }

  @Override
  public StudyViewModel get() {
    return newInstance(repoProvider.get(), savedStateProvider.get());
  }

  public static StudyViewModel_Factory create(Provider<CardRepository> repoProvider,
      Provider<SavedStateHandle> savedStateProvider) {
    return new StudyViewModel_Factory(repoProvider, savedStateProvider);
  }

  public static StudyViewModel newInstance(CardRepository repo, SavedStateHandle savedState) {
    return new StudyViewModel(repo, savedState);
  }
}
