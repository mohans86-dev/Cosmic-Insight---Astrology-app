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
  <img src="screenshots/splash.png" width="200" alt="Splash Screen">
  <img src="screenshots/home.png" width="200" alt="Home Screen">
  <img src="screenshots/birth_input.png" width="200" alt="Birth Input">
  <img src="screenshots/kundli_chart.png" width="200" alt="Kundli Chart">
</p>

<p align="center">
  <img src="screenshots/planet_positions.png" width="200" alt="Planet Positions">
  <img src="screenshots/planet_detail.png" width="200" alt="Planet Detail">
  <img src="screenshots/sign_profile.png" width="200" alt="Sign Profile">
  <img src="screenshots/dasha_timeline.png" width="200" alt="Dasha Timeline">
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

## 🎨 Design System

The app uses a custom **cosmic dark theme** with:

| Token | Color | Usage |
|---|---|---|
| `cosmic_deep_navy` | `#0A0E27` | Primary background |
| `cosmic_indigo` | `#1A1B4B` | Cards & surfaces |
| `cosmic_purple` | `#2D1B69` | Containers & accents |
| `cosmic_gold` | `#FFD700` | Primary accent & text highlights |
| `cosmic_amber` | `#FFAB40` | Secondary accent |
| `element_fire` | `#FF6B35` | Fire signs (Aries, Leo, Sagittarius) |
| `element_earth` | `#4CAF50` | Earth signs (Taurus, Virgo, Capricorn) |
| `element_air` | `#42A5F5` | Air signs (Gemini, Libra, Aquarius) |
| `element_water` | `#7E57C2` | Water signs (Cancer, Scorpio, Pisces) |

---

## 🗺️ Roadmap

- [ ] Add more cities to the built-in database
- [ ] Implement North Indian diamond-style chart layout
- [ ] Add Antardasha (sub-period) calculations
- [ ] Transit chart overlay
- [ ] Chart comparison (synastry)
- [ ] Export chart as image / PDF
- [ ] Detailed house-level interpretations
- [ ] Yogas (planetary combinations) analysis
- [ ] Widget for daily cosmic insight

---

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

Please make sure your code follows the existing architecture patterns and includes appropriate documentation.

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

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
