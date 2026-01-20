# CHANGELOG

Tous les changements notables de ce projet sont documentés dans ce fichier.

## [1.0.0] - 2024-01-20

### 🎉 Release Initial

#### ✨ Features
- **Multi-Format Support**: PDF, EPUB, TXT, MOBI
- **4 Themes**: Light, Dark, Sepia, AMOLED
- **Reading Management**:
  - Bookmarks support
  - Reading history with auto-resume
  - Progress tracking
- **Library Management**:
  - Document collection
  - Search functionality
  - File organization
- **Built-in File Explorer**:
  - Storage browsing
  - Multi-format import
  - Sort by name, date, size
- **Reader Features**:
  - Smooth page navigation
  - Text search
  - Brightness control
  - Zoom support (PDF)
  - Responsive design
- **Customization**:
  - Font size adjustment
  - Reading mode (page/scroll)
  - Theme switching
- **Performance Optimized**:
  - APK ~10MB
  - Low memory footprint
  - Fast loading
- **Privacy Focused**:
  - No ads
  - No tracking
  - No cloud services
  - Offline-first

#### 🏗️ Technical
- Pure Kotlin + MVVM architecture
- Room database for persistence
- Koin for dependency injection
- Kotlin Coroutines for async operations
- Material Design 3
- ProGuard minification

### 📱 Supported Formats
- **PDF**: via PDFium (fast & lightweight)
- **EPUB**: via Readium (standard compliant)
- **TXT**: Built-in text reader
- **MOBI**: Basic support

### 🎨 Themes
- Light Theme
- Dark Theme
- Sepia Theme
- AMOLED Theme

### 📋 Minimum Requirements
- Android 5.0 (API 21)
- ~20MB storage space

---

## [Unreleased]

### 🚧 Planned Features
- [ ] Book cover thumbnails
- [ ] Collections/categories
- [ ] Text-to-speech (TTS)
- [ ] Dictionary integration
- [ ] Reading statistics
- [ ] Cloud sync (optional)
- [ ] Highlights & annotations
- [ ] Dark mode scheduling
- [ ] Reading time estimates
- [ ] Multi-language support
- [ ] Accessibility improvements
- [ ] Widget support

### 🔮 Future Enhancements
- Better MOBI support
- CBR/CBZ comic format
- Advanced search (regex)
- Custom fonts
- Margin & spacing settings
- Table of contents navigation
- Offline content library
- Social sharing features

---

## Standards de Versioning

Ce projet suit [Semantic Versioning](https://semver.org/):
- **MAJOR**: Changements incompatibles
- **MINOR**: Nouvelles fonctionnalités compatibles
- **PATCH**: Corrections de bugs

Format: `MAJOR.MINOR.PATCH`

Example: `1.2.3` → `2.0.0` (breaking changes)

---

## Types de Changements

- **✨ Features**: Nouvelles fonctionnalités
- **🐛 Fixed**: Corrections de bugs
- **💥 Changed**: Changements existants
- **🗑️ Removed**: Fonctionnalités supprimées
- **📚 Docs**: Documentation updates
- **⚡ Performance**: Améliorations performance
- **🔒 Security**: Corrections de sécurité
- **🎨 Style**: Changements de style/formatage
- **🧪 Tests**: Ajout/modification de tests
- **🔧 Chore**: Changements de build/deps

---

## Releases Archive

Voir [GitHub Releases](https://github.com/wabosphere/literead/releases)

---

## Comment Rapporter un Bug

1. Vérifiez si le bug existe déjà
2. Créez une [issue GitHub](https://github.com/wabosphere/literead/issues)
3. Incluez:
   - Description claire
   - Version de l'app
   - Appareil Android et version
   - Étapes pour reproduire
   - Comportement attendu vs réel

---

## Contribution

Voir [CONTRIBUTING.md](CONTRIBUTING.md) pour les guidelines.
