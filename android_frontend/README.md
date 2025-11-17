# Simple Quiz - Android (Compose)

A simple quiz application built with Jetpack Compose and Material 3, using the Ocean Professional theme (primary #2563EB, secondary/success #F59E0B, error #EF4444, background #f9fafb, surface #ffffff, text #111827). It is a single-activity app using Navigation-Compose with in-memory quiz data.

## Features
- Quiz List screen with titles, descriptions, and Start buttons
- Quiz screen with paged questions (Previous/Next), single-choice selection, and Submit
- Results screen with score, percentage, Retry, and Back to list
- Modern, rounded UI with subtle shadows

## Project layout (android_frontend/app)
- Main activity: `app/src/main/kotlin/org/example/app/MainActivity.kt`
- Navigation: `app/src/main/kotlin/org/example/app/navigation/NavGraph.kt`
- Theme: `app/src/main/kotlin/org/example/app/ui/theme/Theme.kt`
- Screens:
  - `ui/screens/QuizListScreen.kt`
  - `ui/screens/QuizScreen.kt`
  - `ui/screens/ResultsScreen.kt`
- Models: `app/src/main/kotlin/org/example/app/model/Models.kt`
- Data (in-memory): `app/src/main/kotlin/org/example/app/data/InMemoryQuizRepository.kt`

## Build and Run

From the `android_frontend` directory:

```bash
# Build
./gradlew :app:assembleDebug

# Install to a connected device/emulator
./gradlew :app:installDebug
```

Then, find the app named "Simple Quiz" on the device and launch it.

You can also build the whole multi-project:
```bash
./gradlew build
```

## Notes
- No networking or external services. All data is in-memory.
- Single-activity architecture with Navigation-Compose.
- Compose Preview is available for individual Composables if desired (Material3 and UI tooling dependencies are included).