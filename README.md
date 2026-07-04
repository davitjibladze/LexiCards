# LexiCards 🃏

A flashcard-based language learning app for Android. Create decks of word cards, study them using spaced repetition, and track your progress.

---

## Features

- **Create decks** for any foreign language (English, German, French, etc.)
- **Add cards** with a foreign word on one side and the Georgian translation on the other
- **Study mode** — type your answer (text input), not multiple choice
- **Spaced Repetition (SM-2)** — cards you forget appear more often; mastered cards appear less
- **Flexible study direction** — choose whether to see the foreign word or Georgian word as the prompt
- **Typo tolerance** — minor spelling mistakes are accepted with a warning
- **Stats screen** — see total cards, decks, cards due today, and mastered cards
- **Cloud sync** — all data synced to Firebase Realtime Database
- **Offline support** — Room local database keeps everything working without internet

---

## Technical Details

| Layer | Technology |
|---|---|
| Language | Kotlin |
| Architecture | MVVM (ViewModel + StateFlow) |
| UI | XML Layouts + View Binding (no `findViewById`) |
| Navigation | Navigation Component (NavGraph + SafeArgs) |
| Local DB | Room |
| Remote DB | Firebase Realtime Database |
| DI | Hilt |
| Async | Coroutines + Flow |
| New functionality | SM-2 Spaced Repetition Algorithm |

---

## Architecture Overview

```
com.lexicards
├── data
│   ├── local
│   │   ├── dao/          # Room DAOs (CardDao, DeckDao)
│   │   └── LexiDatabase  # Room database
│   ├── remote
│   │   └── FirebaseDataSource
│   └── repository
│       └── CardRepository  # Single source of truth
├── di
│   └── AppModule           # Hilt dependency injection
├── model
│   ├── Card                # Room entity + SM-2 fields
│   ├── Deck                # Room entity
│   └── StudyModels         # ReviewQuality, Language enums
├── ui
│   ├── MainActivity        # Navigation Drawer host
│   ├── deck/               # DeckListFragment + ViewModel + Adapter
│   ├── cards/              # CardListFragment + ViewModel + Adapter
│   ├── study/              # StudyFragment + ViewModel
│   └── stats/              # StatsFragment + ViewModel
└── utils
    └── SpacedRepetition    # SM-2 algorithm + answer checker
```

---

## SM-2 Spaced Repetition Algorithm

The core new feature of LexiCards. After each answer, the card's next review date is calculated:

- **Correct answer** → interval increases (1 day → 6 days → grows by ease factor)
- **Wrong answer** → card resets to interval = 1 day and is put back in the current session queue
- **Ease Factor** adjusts per card based on how often you get it right
- Cards with interval ≥ 21 days are considered **mastered**

Answer checking also supports **typo tolerance** using Levenshtein distance (distance ≤ 1 is accepted as "almost correct").

---

## Setup

1. Create a Firebase project at [console.firebase.google.com](https://console.firebase.google.com)
2. Enable **Realtime Database**
3. Download `google-services.json` and place it in `app/`
4. Set Realtime Database rules to allow read/write (for development):
```json
{
  "rules": {
    ".read": true,
    ".write": true
  }
}
```
5. Build and run the project in Android Studio

---

## Requirements

- Android Studio Hedgehog or newer
- Min SDK: 26 (Android 8.0)
- Target SDK: 34

---

## Notes

- The app requires at least **10 cards** in a deck before the study session can begin
- Study direction is configurable per session: Foreign → Georgian or Georgian → Foreign
- All answers are text input (no multiple choice), which helps with spelling practice
