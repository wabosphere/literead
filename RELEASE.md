# Release & Publication Guide - LiteRead

## 📋 Release Checklist

Before releasing a new version:

- [ ] All tests pass
- [ ] No lint errors
- [ ] Code reviewed
- [ ] Version number updated
- [ ] CHANGELOG.md updated
- [ ] All features tested manually
- [ ] Performance optimized
- [ ] Security audit done
- [ ] Documentation updated
- [ ] Screenshots/videos prepared

---

## 🔄 Release Process

### Step 1: Prepare Release

```bash
# 1. Update version in app/build.gradle.kts
versionCode = 2
versionName = "1.1.0"

# 2. Update CHANGELOG.md with new features
# Add under [Unreleased] section

# 3. Test thoroughly
./test.sh

# 4. Build release versions
./build.sh release

# 5. Commit changes
git add .
git commit -m "chore: prepare release v1.1.0"
```

### Step 2: Create Git Tag

```bash
# Tag the release
git tag -a v1.1.0 -m "Release v1.1.0"

# Verify tag
git tag -l

# Push tag to GitHub
git push origin v1.1.0
```

### Step 3: GitHub Actions Triggers

When you push a tag `v1.1.0`:
1. `release-signed.yml` workflow starts
2. Builds signed APK & AAB
3. Creates GitHub Release
4. Uploads artifacts

**Status check:** GitHub → Actions → Release

### Step 4: GitHub Release

Release is auto-created with:
- Pre-formatted release notes
- APK files (universal + per-arch)
- AAB file (Play Store)
- Download links

### Step 5: Google Play Store (Optional)

```bash
# If publishing to Play Store:

1. Go to Google Play Console
2. Select LiteRead app
3. Create new release
4. Upload AAB file
5. Add release notes
6. Review and publish
```

---

## 📦 Build Artifacts

After release workflow completes:

```
Artifacts:
├── LiteRead-universal-release.apk   # All devices
├── LiteRead-arm64-v8a-release.apk   # 64-bit phones (modern)
├── LiteRead-armeabi-v7a-release.apk # 32-bit phones (older)
├── LiteRead-x86-release.apk         # Tablets/Emulators
├── LiteRead-x86_64-release.apk      # 64-bit tablets
└── LiteRead-release.aab             # Play Store
```

---

## 🔐 Signing Configuration

### Setup Signing Keys (First Time)

```bash
# 1. Generate keystore (keep this safe!)
keytool -genkey -v -keystore literead-release.keystore \
  -keyalg RSA -keysize 2048 -validity 10000 -alias literead

# 2. Keep passwords safe (don't commit!)
export KEYSTORE_PATH=/path/to/literead-release.keystore
export KEYSTORE_PASSWORD=your_password
export KEY_ALIAS=literead
export KEY_PASSWORD=your_key_password

# 3. Verify
keytool -list -v -keystore literead-release.keystore
```

### GitHub Secrets Setup

1. **Encode keystore:**
```bash
base64 literead-release.keystore > keystore.b64
```

2. **Add to GitHub Secrets:**
   - Go to: Repository → Settings → Secrets → Actions
   - Add `SIGNING_KEYSTORE_BASE64`
   - Add `SIGNING_KEYSTORE_PASSWORD`
   - Add `SIGNING_KEY_ALIAS`
   - Add `SIGNING_KEY_PASSWORD`

3. **Test:**
```bash
# Workflow auto-decodes and signs APKs
```

---

## 📝 Version Numbering

Follow Semantic Versioning: `MAJOR.MINOR.PATCH`

- **MAJOR** (v2.0.0): Breaking changes, major features
- **MINOR** (v1.2.0): New features, backward compatible
- **PATCH** (v1.1.1): Bug fixes

Examples:
- v1.0.0 - Initial release
- v1.1.0 - Add new theme feature
- v1.1.1 - Fix crash bug
- v2.0.0 - Redesign UI (breaking changes)

---

## 📱 Google Play Store

### Prerequisites
- Google Developer Account ($25 one-time)
- Signing key (created above)
- App listing created in Play Console

### Upload to Play Store

```bash
# 1. Build release AAB
./gradlew bundleRelease

# 2. In Play Console:
#    - Select LiteRead app
#    - Create new release
#    - Upload AAB: app/build/outputs/bundle/release/app-release.aab
#    - Add release notes
#    - Review content rating
#    - Set rollout percentage (e.g., 10% for phased)
#    - Review and publish

# 3. Monitor:
#    - Check crash reports
#    - Monitor ratings/reviews
#    - Check ANRs (Application Not Responding)
```

### Play Store Optimization

```
Asset Sizing:
- APK size: < 15MB ideal
- Target < 10MB
- Strip unused resources

Screenshots:
- Min: 1280 x 720px
- Include: UI, features, themes
- Create for: Phone, Tablet

Description:
- Keep clear and concise
- Highlight unique features
- List requirements

Keywords:
- document reader
- PDF viewer
- EPUB reader
- lightweight app

Categories:
- Books & Reference
- Productivity
```

---

## 🚀 Pre-Release Testing

### Manual Testing
- [ ] Install on real device
- [ ] Open each supported format
- [ ] Test all 4 themes
- [ ] Test bookmarks
- [ ] Test search
- [ ] Test permissions
- [ ] Test file import
- [ ] Test landscape mode
- [ ] Test navigation
- [ ] Battery/memory usage

### Device Testing
- [ ] Modern phone (Android 12+)
- [ ] Older phone (Android 5.0+)
- [ ] Tablet
- [ ] Emulator
- [ ] Different architectures (if possible)

### Format Testing
- [ ] Small PDF
- [ ] Large PDF
- [ ] PDF with images
- [ ] Simple EPUB
- [ ] Complex EPUB
- [ ] TXT file
- [ ] MOBI file

---

## 📊 Analytics & Monitoring

### GitHub Analytics
```bash
# Download counts
# Release → (view all) → downloads shown

# Workflow stats
# Actions → workflows → see run times
```

### Google Play Analytics (if published)
```
Monitor:
- Downloads/installs
- Active installs
- Uninstalls
- Ratings (stars)
- Reviews
- Crashes
- ANRs
- Errors
```

### Performance Monitoring
```bash
# Check APK size
du -h app/build/outputs/apk/release/app-release.apk

# Monitor growth over releases
# Track in spreadsheet
```

---

## 🔔 Release Announcement

### Announce Release

1. **GitHub Release** (auto-generated)
   - Release notes included
   - Download links ready

2. **Social Media** (Optional)
   ```
   🎉 LiteRead v1.1.0 Released!
   
   New features:
   - Feature 1
   - Feature 2
   - Improvements & bug fixes
   
   Download: [GitHub link]
   ```

3. **Community**
   - Reddit r/Android
   - HN (if significant)
   - Dev communities

---

## 🔄 Post-Release

### Monitor Issues
```bash
# Check crash reports (Play Store)
# Monitor GitHub issues
# Review user feedback

# If critical bug found:
git hotfix branch
Quick patch release
Tag as v1.1.1
```

### Update Documentation
- [ ] Update CHANGELOG.md
- [ ] Update README.md if needed
- [ ] Update feature list
- [ ] Update screenshots if UI changed

### Plan Next Release
- [ ] Review user feedback
- [ ] Plan features for v1.2.0
- [ ] Create GitHub milestones
- [ ] Assign tasks

---

## 📚 Release History

See [CHANGELOG.md](CHANGELOG.md) for all releases.

---

## 🆘 Troubleshooting

### Release Build Fails
```bash
# Clean and retry
./gradlew clean bundleRelease assembleRelease

# Check gradle version
./gradlew --version

# Increase heap
export GRADLE_OPTS="-Xmx4096m"
```

### APK Size Too Large
```bash
# Enable resource shrinking
isShrinkResources = true
isMinifyEnabled = true

# Remove unused dependencies
./gradlew dependencies --configuration release
```

### Play Store Upload Fails
```
Common issues:
- Signing key mismatch
- Version code too low (must increase each release)
- APK format issue (use AAB for Play Store)
- Metadata issues (screenshots, description)
```

---

## 📞 Support

For release issues:
- Check GitHub Actions logs
- See SUPPORT.md
- Create GitHub issue

---

## 🎓 References

- [Play Store Guidelines](https://play.google.com/console)
- [Android Release Process](https://developer.android.com/studio/publish)
- [App Signing](https://developer.android.com/studio/publish/app-signing)
- [Semantic Versioning](https://semver.org/)

---

**Last Updated**: 2024-01-20
