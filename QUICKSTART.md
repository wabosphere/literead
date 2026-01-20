# Quick Start Guide - LiteRead

## ⚡ 5-Minute Quick Start

### 1. Prerequisites
- Java 11 or higher
- Android SDK (minSdk 21)
- Git

### 2. Clone & Setup
```bash
git clone https://github.com/wabosphere/literead.git
cd literead
chmod +x *.sh
./setup.sh
```

### 3. Build & Run
```bash
# Debug APK
./gradlew assembleDebug

# Install on device
adb install app/build/outputs/apk/debug/app-debug.apk

# Or use Android Studio: Run → Run 'app'
```

### 4. Test
```bash
./test.sh
```

---

## 📱 Installation Methods

### Method 1: ADB (Easiest)
```bash
# Build
./gradlew assembleDebug

# Connect device via USB & enable USB debugging
adb install app/build/outputs/apk/debug/app-debug.apk

# Or for emulator
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Method 2: Android Studio
1. Open project in Android Studio
2. Connect device or start emulator
3. Click green ▶️ Run button
4. Select target device

### Method 3: Manual
1. Build: `./gradlew assembleDebug`
2. Find APK: `app/build/outputs/apk/debug/`
3. Copy to device
4. Enable "Unknown sources" in Settings
5. Open APK file

---

## 🛠️ Development Workflow

### Build
```bash
./build.sh debug        # Debug APK
./build.sh release      # Release APK
./build.sh debug clean  # Clean & build
```

### Test
```bash
./test.sh               # All tests
./gradlew test         # Unit tests only
./gradlew lint         # Lint check
```

### Format
```bash
./format.sh            # Format code
./gradlew ktlintCheck  # Check style
```

### IDE Integration
Open in Android Studio:
- File → Open
- Select `literead` folder
- Wait for indexing
- Start editing!

---

## 📦 Architecture Support

The app builds for multiple architectures:

```bash
./gradlew assembleDebug  # All archs (universal)
```

Individual architecture builds:
- `arm64-v8a` - Modern phones (64-bit)
- `armeabi-v7a` - Older phones (32-bit)
- `x86` - Tablets
- `x86_64` - 64-bit tablets
- `universal` - All in one APK

---

## 🔍 Debugging

### Logcat
```bash
# View real-time logs
adb logcat

# Filter by app
adb logcat | grep literead

# Specific log level
adb logcat *:E  # Errors only
```

### Android Studio Debugger
1. Set breakpoints (click line number)
2. Run → Debug 'app'
3. App pauses at breakpoint
4. Inspect variables in Debug panel

### Device Info
```bash
# Check device
adb devices

# Get device logs
adb bugreport
```

---

## 📁 Project Structure

```
literead/
├── app/                           # Main app module
│   ├── src/
│   │   └── main/
│   │       ├── java/             # Kotlin code
│   │       └── res/              # Resources
│   ├── build.gradle.kts          # Dependencies
│   └── proguard-rules.pro        # Obfuscation
├── .github/workflows/            # CI/CD
├── build.gradle.kts              # Root build config
├── settings.gradle.kts
├── build.sh                      # Build script
├── test.sh                       # Test script
├── setup.sh                      # Setup script
└── README.md
```

---

## ⚙️ Configuration

### Change Version
Edit `app/build.gradle.kts`:
```kotlin
defaultConfig {
    versionCode = 1           # Increment for each release
    versionName = "1.0.0"     # X.Y.Z format
}
```

### Change Min/Target SDK
Edit `app/build.gradle.kts`:
```kotlin
defaultConfig {
    minSdk = 21               # Android 5.0+
    targetSdk = 34            # Latest Android
}
```

### Add Dependencies
Edit `app/build.gradle.kts`:
```kotlin
dependencies {
    implementation("group:artifact:version")
    testImplementation("test:library:version")
}
```

---

## 🐛 Troubleshooting

### Issue: Gradle sync fails
```bash
# Clean and retry
./gradlew clean
./gradlew build
```

### Issue: APK not installing
```bash
# Check device
adb devices

# Uninstall old version
adb uninstall com.literead

# Reinstall
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Issue: "targetSdk must be at least 31"
Update `build.gradle.kts` with latest SDK

### Issue: Port already in use
```bash
# Kill process using port
lsof -ti:5037 | xargs kill -9
```

### Issue: Out of memory
```bash
# Increase heap
export GRADLE_OPTS="-Xmx4096m"
```

---

## 📚 Learning Resources

- [Android Developer Docs](https://developer.android.com)
- [Kotlin Official Guide](https://kotlinlang.org)
- [MVVM Architecture Pattern](https://developer.android.com/jetpack/guide)
- [Room Database](https://developer.android.com/training/data-storage/room)
- [LiveData & ViewModel](https://developer.android.com/topic/libraries/architecture/livedata)

---

## 🤝 Getting Help

- Read [DOCUMENTATION.md](DOCUMENTATION.md)
- Check [DEVELOPMENT.md](DEVELOPMENT.md)
- Review [CONTRIBUTING.md](CONTRIBUTING.md)
- Create [GitHub Issue](https://github.com/wabosphere/literead/issues)

---

## 📝 Next Steps

1. ✅ Project setup complete
2. 📖 Read DOCUMENTATION.md for architecture
3. 🚀 Start with a simple feature
4. 🧪 Write tests
5. 📤 Submit a PR!

---

**Happy coding! 🎉**
