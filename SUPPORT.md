# Support & Troubleshooting - LiteRead

## 🆘 Troubleshooting Guide

### Build Issues

#### Gradle Sync Fails
```
Error: Gradle sync failed

Solution:
1. File → Invalidate Caches / Restart
2. ./gradlew clean
3. ./gradlew build
```

#### OutOfMemoryError
```
Error: java.lang.OutOfMemoryError: Java heap space

Solution:
export GRADLE_OPTS="-Xmx4096m -Xms512m"
./gradlew clean build
```

#### "targetSdk must be at least X"
```
Solution: Update build.gradle.kts targetSdk value
or
Update Android SDK in Android Studio
```

#### NDK Not Found
```
Error: NDK not found

Solution:
1. Android Studio → Tools → SDK Manager
2. SDK Tools → NDK (Side by side)
3. Install latest NDK
```

---

### Runtime Issues

#### App Crashes on Launch
```
Solution:
1. Check Logcat: adb logcat | grep literead
2. Look for exceptions in logs
3. Ensure minSdk device version >= 21
4. Check file permissions
```

#### Document Won't Open
```
Possible causes:
1. File format not supported
   → Check if it's PDF/EPUB/TXT/MOBI
   → Use file command: file document.pdf
   
2. File corrupted
   → Try with different file
   → Verify file integrity
   
3. Permission denied
   → Check file permissions
   → Ensure READ_EXTERNAL_STORAGE granted
```

#### Slow Performance
```
Solution:
1. Check RAM usage: adb shell dumpsys meminfo
2. Monitor CPU: adb shell top -m 10
3. Close other apps
4. Clear app cache: adb shell pm clear com.literead
5. Restart device
```

#### Memory Leak
```
Solution:
1. Use Android Profiler
2. Check for static references
3. Properly close resources
4. Use WeakReference if needed
```

---

### Permission Issues

#### "Permission denied" error
```
Solution:
1. Android 6+: Request runtime permissions
2. Check AndroidManifest.xml permissions
3. Grant permissions in Settings → Apps → LiteRead
4. Test with: adb shell pm grant com.literead android.permission.READ_EXTERNAL_STORAGE
```

#### "No permission to read file"
```
Solution:
1. Ensure file path is accessible
2. Check file ownership
3. Use context.getExternalFilesDir() for app-specific
4. Use File.createTempFile() for temp files
```

---

### Installation Issues

#### APK Won't Install
```
adb install app-debug.apk
Error: INSTALL_PARSE_FAILED_NOT_APK

Solution 1: Wrong ABI
→ Ensure device matches architecture

Solution 2: Corrupted APK
→ Rebuild: ./gradlew clean assembleDebug

Solution 3: App already installed
→ adb uninstall com.literead
→ adb install app-debug.apk
```

#### Version conflict
```
Error: INSTALL_FAILED_VERSION_DOWNGRADE

Solution:
adb uninstall com.literead
adb install new-version.apk
```

#### Insufficient storage
```
Error: INSTALL_FAILED_INSUFFICIENT_STORAGE

Solution:
1. Free device storage
2. Build for specific ABI instead of universal
```

---

### Database Issues

#### Database corruption
```
Error: SQLite database is corrupted

Solution:
1. Clear app data
2. Reinstall app
3. In code: use fallbackToDestructiveMigration()
```

#### Can't write to database
```
Solution:
1. Check device has free space
2. Verify app has write permission
3. Check database file permissions
4. Try on different device/emulator
```

---

## 📊 Performance Optimization

### APK Size
```bash
# Check APK size
du -h app/build/outputs/apk/debug/app-debug.apk

# Analyze APK
./gradlew bundleDebug
bundletool dump manifest --bundle=path/to/app.aab

# Remove unused resources
isShrinkResources = true
isMinifyEnabled = true
```

### Memory Usage
```bash
# Monitor with Profiler
Android Studio → Profiler → Memory

# Heap dump
adb shell am dumpheap com.literead /data/local/tmp/heap.bin
adb pull /data/local/tmp/heap.bin
```

### Battery Usage
```bash
# Use Battery Historian
# Avoid:
# - Frequent wake locks
# - High-frequency timers
# - Continuous GPS/WiFi
```

---

## 🔐 Security Checklist

- [ ] No hardcoded secrets/keys
- [ ] No sensitive data in logs
- [ ] Validate user input
- [ ] Use SSL/TLS for network
- [ ] Proper permission handling
- [ ] No SQL injection vulnerabilities
- [ ] Secure file storage
- [ ] Obfuscate with ProGuard
- [ ] Update dependencies regularly
- [ ] Run security scans

---

## 📱 Device & Emulator Issues

### Emulator Won't Start
```
Solution:
1. Check virtualization enabled in BIOS
2. Update GPU drivers
3. Wipe data: emulator -avd MyAVD -wipe-data
4. Recreate AVD in Android Studio
```

### Slow Emulator
```
Solution:
1. Use x86/x86_64 ABI (not ARM)
2. Allocate more RAM/CPU cores
3. Use hardware acceleration
4. Disable animations: Settings → Developer → Animation scale 0
```

### Device Not Recognized
```
adb devices
# Shows: ??????  unauthorized

Solution:
1. Disconnect device
2. Accept USB debugging prompt on device
3. Reconnect
4. chmod 666 for udev rules (Linux)
```

---

## 🐛 Bug Reports

### Before Reporting
- [ ] Try latest version
- [ ] Clear app cache
- [ ] Restart device
- [ ] Try different device/file
- [ ] Check existing issues

### When Reporting
Include:
1. Device model & Android version
2. LiteRead version
3. What you were doing
4. What happened (expected vs actual)
5. Logcat output
6. Screenshots/videos if applicable

```bash
# Get detailed logs
adb logcat -v threadtime > debug.log
# Reproduce issue
# Share debug.log with bug report
```

### Create Issue
1. GitHub → Issues → New issue
2. Use provided template
3. Be descriptive and clear
4. Provide reproduction steps

---

## 🤝 Getting Help

### Documentation
- [README.md](README.md) - Overview
- [DOCUMENTATION.md](DOCUMENTATION.md) - Full docs
- [DEVELOPMENT.md](DEVELOPMENT.md) - Dev guide
- [QUICKSTART.md](QUICKSTART.md) - Quick start
- [CONTRIBUTING.md](CONTRIBUTING.md) - Contributing

### Community
- GitHub Issues: https://github.com/wabosphere/literead/issues
- GitHub Discussions: https://github.com/wabosphere/literead/discussions
- Kotlin Slack: https://kotlinlang.slack.com
- Android Dev Discord: https://discord.gg/android

### External Resources
- [Android Developers](https://developer.android.com)
- [Stack Overflow](https://stackoverflow.com/questions/tagged/android)
- [Kotlin Documentation](https://kotlinlang.org/docs/)
- [Material Design](https://material.io)

---

## 📈 Common Questions (FAQ)

**Q: Where is my data stored?**
A: In device's internal storage (not cloud). Data stays private on your device.

**Q: Does it work offline?**
A: Yes! LiteRead is fully offline. No internet needed.

**Q: Can I sync across devices?**
A: Not currently. Data is per-device. This may be added as optional feature.

**Q: What formats are supported?**
A: PDF, EPUB, TXT, MOBI. Basic support for each.

**Q: Why is APK large?**
A: Contains all architectures. Download specific ABI for smaller file.

**Q: Can I export my data?**
A: Backup your device or access through file manager.

**Q: Is there a web version?**
A: No, this is Android-only app.

**Q: Can I contribute?**
A: Yes! See CONTRIBUTING.md

---

## 📞 Contact & Support

- **Email**: [support email if applicable]
- **GitHub**: @wabosphere/literead
- **Issues**: https://github.com/wabosphere/literead/issues
- **Discussions**: https://github.com/wabosphere/literead/discussions

---

**Last Updated**: 2024-01-20

For latest info, check: https://github.com/wabosphere/literead
