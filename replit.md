# LiteRead - Replit Configuration

## Overview

LiteRead is an ultra-lightweight Android document reader application built with Kotlin. It supports multiple document formats (PDF, EPUB, TXT, MOBI) with a focus on performance, privacy, and simplicity. The app features a clean MVVM architecture, local-only data storage, and minimal resource usage (~10MB APK, <100MB RAM).

## User Preferences

Preferred communication style: Simple, everyday language.

## System Architecture

### Core Architecture Pattern
- **MVVM (Model-View-ViewModel)**: Clear separation between UI components and business logic
- **Repository Pattern**: Abstraction layer between data sources and ViewModels
- **Dependency Injection**: Koin for lightweight DI container

### Frontend Architecture
- **Pure Kotlin**: No Java code, fully Kotlin-based
- **Material Design 3**: Modern Android UI components
- **Fragments + Activities**: Standard Android UI architecture with proper lifecycle management
- **Themes**: 4 built-in themes (Light, Dark, Sepia, AMOLED)

### Data Layer
- **Room Database**: SQLite wrapper for local persistence (documents, bookmarks, reading history)
- **SharedPreferences**: User preferences and settings storage
- **DAOs**: Structured data access objects for each entity type

### Document Rendering
- **PDF**: PDFium library (fast, lightweight native renderer)
- **EPUB**: Readium Navigator for standard e-book rendering
- **TXT**: Native text rendering
- **MOBI**: Basic custom parser

### Async Operations
- **Kotlin Coroutines**: All async operations use coroutines for clean, non-blocking code
- **Flow**: Reactive data streams for UI updates

### Build Configuration
- **Target SDK**: 34
- **Min SDK**: 21 (Android 5.0)
- **Kotlin Version**: 1.9.22
- **JDK**: 11+
- **ProGuard**: Enabled for release builds with minification and obfuscation

### Privacy-First Design
- Fully offline operation
- No analytics or tracking
- No cloud services
- All data stays on device

## External Dependencies

### Core Android Libraries
- `androidx.appcompat:appcompat:1.6.1`
- `androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2`
- `androidx.fragment:fragment-ktx:1.6.2`
- `com.google.android.material:material:1.10.0`

### Database
- `androidx.room:room-runtime:2.6.1`
- `androidx.room:room-ktx` (coroutines support)

### Dependency Injection
- `io.insert-koin:koin-android:3.5.0`

### Async & Serialization
- `org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3`
- `org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0`

### Document Readers
- `io.github.javacpp:pdfium` - PDF rendering
- `org.readium.kotlin-toolkit:readium-navigator:2.4.0` - EPUB support

### CI/CD
- GitHub Actions workflows for build, lint, security scanning, and release automation
- Multi-architecture APK builds (arm64-v8a, armeabi-v7a, x86, x86_64)