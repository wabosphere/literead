# Keystore Configuration for LiteRead

## Setting up APK Signing

### Generate Keystore (First time only)

```bash
# Generate a new keystore
keytool -genkey -v -keystore literead-release.keystore -keyalg RSA -keysize 2048 -validity 10000 -alias literead

# You'll be asked for:
# - Keystore password
# - Key password
# - Full Name
# - Organizational Unit
# - Organization
# - City/Locality
# - State/Province
# - Country Code (2 letters)
```

### Store Credentials Safely

```bash
# Export environment variables (don't commit these!)
export KEYSTORE_PATH=/path/to/literead-release.keystore
export KEYSTORE_PASSWORD=your_keystore_password
export KEY_ALIAS=literead
export KEY_PASSWORD=your_key_password
```

### Add to build.gradle.kts

```kotlin
android {
    ...
    signingConfigs {
        create("release") {
            storeFile = file(System.getenv("KEYSTORE_PATH") ?: "keystore/literead-release.keystore")
            storePassword = System.getenv("KEYSTORE_PASSWORD")
            keyAlias = System.getenv("KEY_ALIAS") ?: "literead"
            keyPassword = System.getenv("KEY_PASSWORD")
        }
    }
    
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
```

### GitHub Actions Setup

1. **Generate Base64 encoded keystore:**
```bash
base64 literead-release.keystore > keystore.txt
```

2. **Add to GitHub Secrets:**
   - Go to Repository Settings → Secrets → Actions
   - Add `SIGNING_KEYSTORE_BASE64` with encoded keystore
   - Add `SIGNING_KEYSTORE_PASSWORD`
   - Add `SIGNING_KEY_ALIAS`
   - Add `SIGNING_KEY_PASSWORD`

3. **In workflow:**
```yaml
- name: Setup keystore
  env:
    SIGNING_KEYSTORE: ${{ secrets.SIGNING_KEYSTORE_BASE64 }}
    SIGNING_KEYSTORE_PASSWORD: ${{ secrets.SIGNING_KEYSTORE_PASSWORD }}
  run: |
    echo $SIGNING_KEYSTORE | base64 -d > app/keystore/literead-release.keystore
    export KEYSTORE_PATH=$(pwd)/app/keystore/literead-release.keystore
    export KEYSTORE_PASSWORD=$SIGNING_KEYSTORE_PASSWORD
```

### Verify Keystore

```bash
# List contents of keystore
keytool -list -v -keystore literead-release.keystore

# Get certificate fingerprint (for Play Store)
keytool -exportcert -alias literead -keystore literead-release.keystore | openssl dgst -sha256
```

## Never commit:
- keystore files
- passwords
- private keys

Keep them in GitHub Secrets or environment variables only!
