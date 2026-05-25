<h1 align="center">✦ Cosmic Insight — Vedic Astrology App ✦</h1>

<p align="center">
  <em>Discover Your Celestial Blueprint</em>
</p>

---

## 🌌 About

**Cosmic Insight** is a beautifully crafted Android app that generates **Vedic (Jyotish) birth charts** based on your date, time, and place of birth. It calculates planetary positions using sidereal astronomy, renders a traditional **South Indian Kundli chart**, and provides detailed astrological interpretations — all completely offline.

> **Disclaimer:** This app is built for educational and entertainment purposes. Astrological calculations use simplified astronomical models and should not be treated as precise ephemeris-level data.

---

## ✨ Features

| Feature | Description |
|---|---|
| 🪐 **Birth Chart Generation** | Enter name, date/time of birth, and place to generate a complete Vedic birth chart |
| 🔲 **South Indian Kundli** | Interactive grid-based chart with tap-to-explore zodiac sign cells |
| 🌟 **Planetary Positions** | All 9 Vedic planets (Navagraha) with sign, degree, house, and Nakshatra |
| 🔄 **Retrograde Detection** | Visual indicators for retrograde planets (℞) |
| 📊 **Dasha Timeline** | Full Vimshottari Mahadasha period calculations from birth |
| ♈ **Sign Profiles** | Detailed zodiac profiles — personality, strengths, weaknesses, lucky numbers & colors |
| 🪐 **Planet Interpretations** | Contextual interpretations for each planet-in-sign combination |
| 💾 **Save & Manage Charts** | Persist charts locally with Room database; view, load, or delete anytime |
| 🏙️ **City Search** | Built-in city database with coordinates & timezone for 20 major cities worldwide |
| 🎨 **Cosmic Dark Theme** | Stunning deep navy & gold UI palette with cosmic gradients |
| 🚀 **Splash Screen** | Elegant animated splash with celestial branding |
| 📱 **Fully Offline** | No internet required — all calculations happen on-device |

---

## 📸 Screenshots

<!-- Add your screenshot files to a /screenshots folder in the repo root -->

<p align="center">
  <img width="217" height="458" alt="splash" src="https://github.com/user-attachments/assets/a6c18d79-4364-4adb-97d3-5fa8e1c1c45e" />
  <img width="217" height="458" alt="home" src="https://github.com/user-attachments/assets/7ba1b32c-ec1d-40c1-98cf-0ef6d45ff145" />
  <img width="217" height="458" alt="birth-input" src="https://github.com/user-attachments/assets/7625526b-f6df-4105-aff8-7e8a432fb190" />
  <img width="217" height="458" alt="kundli-chart" src="https://github.com/user-attachments/assets/318be703-2203-43b3-8005-f9f1e0e121f3" />
</p>

<p align="center">
  <img width="217" height="458" alt="planet-details" src="https://github.com/user-attachments/assets/9f0dc87c-609d-44df-88b3-326ebeef6183" />
  <img width="217" height="458" alt="dasha-timeline" src="https://github.com/user-attachments/assets/4b2cf98f-2f0c-4867-94ab-7cc2e10e4b8d" />
</p>

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| **Language** | Kotlin |
| **UI** | XML Layouts + View Binding |
| **Architecture** | MVVM (ViewModel + LiveData) |
| **Navigation** | Jetpack Navigation Component |
| **Database** | Room Persistence Library |
| **Async** | Kotlin Coroutines + Flow |
| **Serialization** | Gson |
| **Custom Views** | Canvas-based Kundli chart rendering |
| **Build System** | Gradle (Kotlin DSL) + Version Catalog |

---

## 🏗️ Architecture

The app follows a clean **MVVM architecture** with a clear separation of concerns:

```
┌─────────────────────────────────────────┐
│                UI Layer                 │
│  Fragments · ViewModels · Custom Views  │
├─────────────────────────────────────────┤
│             Repository Layer            │
│         ChartRepository (Gson)          │
├──────────────────┬──────────────────────┤
│   Local Data     │   Calculation Engine │
│  Room Database   │  AstrologyCalculator │
│  ChartDao        │  (Sidereal Astro)    │
├──────────────────┴──────────────────────┤
│             Data Models                 │
│  BirthChart · PlanetPosition · Dasha    │
│  ZodiacSign · Nakshatra · HouseInfo     │
└─────────────────────────────────────────┘
```

---

## 📂 Project Structure

```
app/src/main/java/com/example/astrologyapp/
│
├── AstrologyApp.kt                 # Application class — Room DB & repository init
├── MainActivity.kt                 # Single-activity host with NavController
│
├── data/
│   ├── model/
│   │   ├── Enums.kt                # Planet, ZodiacSign, Nakshatra, Element, Quality
│   │   ├── BirthChart.kt           # Core chart data class
│   │   ├── BirthDetails.kt         # User input data class
│   │   ├── PlanetPosition.kt       # Planet placement in chart
│   │   ├── HouseInfo.kt            # House/Bhava information
│   │   ├── DashaPeriod.kt          # Vimshottari Dasha periods
│   │   ├── ZodiacSignInfo.kt       # Sign personality, strengths, weaknesses
│   │   └── PlanetInterpretation.kt # Planet-in-sign interpretations
│   │
│   ├── local/
│   │   ├── AstrologyCalculator.kt  # Core engine — Julian Day, planetary positions, houses
│   │   ├── ChartDatabase.kt        # Room database definition
│   │   ├── ChartDao.kt             # Data access object for saved charts
│   │   ├── ChartEntity.kt          # Room entity for chart persistence
│   │   └── CityData.kt             # Built-in city database (20 cities)
│   │
│   └── repository/
│       └── ChartRepository.kt      # Mediator between UI & data layers
│
├── ui/
│   ├── splash/
│   │   └── SplashActivity.kt       # Animated splash screen
│   ├── home/
│   │   └── HomeFragment.kt         # Landing page with navigation
│   ├── input/
│   │   ├── BirthInputFragment.kt   # Birth details input form
│   │   └── BirthInputViewModel.kt  # Input validation & chart generation
│   ├── chart/
│   │   ├── ChartResultFragment.kt  # Chart display with planet table
│   │   ├── ChartResultViewModel.kt # Chart state management
│   │   └── KundliChartView.kt      # Custom Canvas view — South Indian grid
│   ├── detail/
│   │   ├── PlanetDetailFragment.kt # Individual planet deep-dive
│   │   └── SignProfileFragment.kt  # Zodiac sign profile page
│   ├── dasha/
│   │   ├── DashaTimelineFragment.kt# Mahadasha period timeline
│   │   └── DashaAdapter.kt         # RecyclerView adapter for dasha list
│   └── saved/
│       ├── SavedChartsFragment.kt  # Saved charts list with delete
│       ├── SavedChartsViewModel.kt # Saved charts state management
│       └── SavedChartsAdapter.kt   # RecyclerView adapter for saved charts
│
└── util/
    ├── AstronomyConstants.kt       # J2000 epoch, degree/radian conversions
    └── DateTimeUtils.kt            # Date & time formatting utilities
```

---

## 🔭 Vedic Astrology Concepts

This app implements several core concepts from Jyotish (Vedic astrology):

- **Lahiri Ayanamsa** — Converts tropical (Western) positions to sidereal (Vedic) coordinates
- **Navagraha** — The 9 Vedic celestial bodies: Sun, Moon, Mars, Mercury, Jupiter, Venus, Saturn, Rahu & Ketu
- **12 Rashis** — Zodiac signs with Sanskrit names (Mesha, Vrishabha, Mithuna, etc.)
- **27 Nakshatras** — Lunar mansions from Ashwini to Revati, each with a ruling planet
- **Equal House System** — 12 houses (Bhavas) calculated from the Ascendant
- **Vimshottari Dasha** — 120-year planetary period system based on Moon's Nakshatra
- **South Indian Chart** — Traditional 4×4 grid layout with fixed sign positions

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio** Ladybug or newer
- **JDK 11** or higher
- **Android SDK** with API 36 (compileSdk) and API 26 (minSdk)

### Build & Run

```bash
# Clone the repository
git clone https://github.com/mohans86-dev/Cosmic-Insight---Astrology-app.git

# Open in Android Studio
# File → Open → Select the cloned directory

# Or build from command line
cd Cosmic-Insight---Astrology-app
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug
```

---

## 🙏 Acknowledgments

- Vedic astrology calculation references from traditional Jyotish texts
- [Lahiri Ayanamsa](https://en.wikipedia.org/wiki/Ayanamsa) for sidereal coordinate conversion
- Material Design 3 components by Google
- All zodiac sign symbols and planet glyphs from Unicode standards

---

<p align="center">
  Made with ❤️ and ✨ cosmic energy
</p>

<p align="center">
  <strong>If you find this project useful, consider giving it a ⭐!</strong>
</p>
