# ShilpaKala Installation & Setup Guide

## 📋 Prerequisites

- **Android Studio** 2023.1 (Flamingo) or later
- **Android SDK** API 34
- **Gradle** 8.1.0+
- **Java** JDK 11 or higher
- **Git** (for version control)
- **Minimum Device:** Android 5.0+ (API 24)

## 🔧 Setup Steps

### 1. Clone Repository

```bash
git clone https://github.com/yourrepo/ShilpaKala.git
cd ShilpaKala
```

### 2. Open in Android Studio

- Launch Android Studio
- Select **File → Open**
- Navigate to the ShilpaKala directory
- Wait for Gradle sync to complete

### 3. Install Dependencies

Dependencies are automatically downloaded via Gradle:

```bash
./gradlew build  # Downloads all dependencies
```

### 4. Create Emulator or Connect Device

#### Option A: Use Android Emulator
```bash
# Via Android Studio Device Manager
# Create a new virtual device:
# - Device: Pixel 6 or higher
# - OS: Android 13+ (API 33+)
# - RAM: 4GB+
```

#### Option B: Physical Device
- Enable Developer Mode (tap Build Number 7 times)
- Enable USB Debugging
- Connect via USB cable
- Accept debugging permission

### 5. Build & Run

```bash
# Build debug APK
./gradlew assembleDebug

# Run on connected device/emulator
./gradlew installDebug

# Or use Android Studio Run button (Shift+F10)
```

## 🚨 Common Issues & Solutions

### Issue: Gradle Sync Fails

**Solution:**
```bash
# Clear Gradle cache
./gradlew clean

# Force dependency resolution
./gradlew build --refresh-dependencies
```

### Issue: Camera Permission Denied

**Solution:**
- Grant permissions at runtime via Android Settings
- For emulator: Settings → Apps → ShilpaKala → Permissions → Camera → Allow

### Issue: Image Saving Fails

**Solution:**
- For Android 11+: Request READ/WRITE_EXTERNAL_STORAGE at runtime
- Check device storage space (minimum 100MB recommended)
- Verify app has storage permissions in manifest

### Issue: CameraX Error

**Solution:**
- Ensure device supports Camera2 API (most modern devices do)
- Update to latest CameraX: `androidx.camera:camera-core:1.3.0+`

## 📦 Build Variants

### Debug Build (Development)
```bash
./gradlew assembleDebug
# Output: app/build/outputs/apk/debug/
```

### Release Build (Production)
```bash
./gradlew assembleRelease
# Requires signing configuration (see Signing section)
```

## 🔐 Signing Configuration

For release builds, create `keystore.properties`:

```properties
storeFile=/path/to/keystore.jks
storePassword=your_store_password
keyAlias=your_key_alias
keyPassword=your_key_password
```

Update `app/build.gradle`:

```gradle
signingConfigs {
    release {
        keyStore file(storeProperties['storeFile'])
        keyStorePassword storeProperties['storePassword']
        keyAlias storeProperties['keyAlias']
        keyPassword storeProperties['keyPassword']
    }
}

buildTypes {
    release {
        signingConfig signingConfigs.release
    }
}
```

## 🎯 Project Configuration

### Minimum SDK
- **minSdk:** 24 (Android 7.0)
- **targetSdk:** 34 (Android 14)
- **compileSdk:** 34

### Target Requirements
- Camera hardware capability
- OpenGL ES 2.0+ (for preview rendering)
- 512MB+ RAM recommended

## 📱 Device Testing Checklist

- [ ] Camera opens without lag
- [ ] Live preview is smooth (60 FPS)
- [ ] Overlay renders correctly
- [ ] Image capture saves properly
- [ ] Branding text is readable
- [ ] Gallery loads images quickly
- [ ] WhatsApp sharing works
- [ ] Storage permissions function

## 🐛 Debugging

### Enable Debugging Logs

Add to `MainActivity.kt`:

```kotlin
if (BuildConfig.DEBUG) {
    Log.d("ShilpaKala", "Debug mode enabled")
}
```

### Using Logcat

```bash
# Filter logs
./gradlew logcat | grep ShilpaKala

# Or in Android Studio: View → Tool Windows → Logcat
```

### Remote Debugging

```bash
# Enable remote debugging on device
adb shell setprop debug.force_rtl true

# Check device connection
adb devices
```

## 🚀 Performance Tips

1. **Image Processing:** Process images asynchronously
2. **Memory:** Use bitmap compression to reduce memory footprint
3. **Camera:** Close camera when screen turns off

## 📊 Gradle Commands

```bash
# Clean build
./gradlew clean

# Build all variants
./gradlew build

# Generate release APK
./gradlew assembleRelease

# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Check dependencies
./gradlew dependencies

# Profile build time
./gradlew build --profile
```

## 🔗 Useful Resources

- [CameraX Documentation](https://developer.android.com/training/camerax)
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Android Permissions Guide](https://developer.android.com/guide/topics/permissions/overview)
- [MediaStore API](https://developer.android.com/reference/android/provider/MediaStore)

## 📞 Support

For issues or feature requests:
1. Check existing GitHub issues
2. Create detailed bug report with logcat
3. Include device information (model, API version)

## ✅ First Run Checklist

- [ ] Project imports without errors
- [ ] Gradle sync completes
- [ ] build.gradle versions are correct
- [ ] All dependencies download
- [ ] App compiles successfully
- [ ] APK generates without warnings
- [ ] App launches on device
- [ ] Camera permission dialog appears
- [ ] Camera preview is visible

---

**Happy Coding!** 🎨 Build amazing portfolio apps for artisans worldwide!
