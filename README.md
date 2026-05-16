<<<<<<< HEAD
# ShilpaKala - Digital Portfolio Assistant for Artisans

A modern Android application built with **Jetpack Compose** and **CameraX** that helps artisans transform their craftsmanship into premium-looking digital portfolios.

## 🎯 Features

### 1. **Camera Screen** 
- Live preview with professional framing overlay
- Corner-marked guidance for perfect product shots
- One-tap image capture
- Quick gallery access

### 2. **Branding Screen**
- Add artisan name, wood type, and pricing
- Real-time preview of branded output
- Auto-generated heritage labels with wood-specific descriptions
- Beautiful semi-transparent branding panel with gold accents
- Emoji enhancements for visual appeal

### 3. **Gallery Screen**
- Grid view of all processed images
- Selection with visual feedback
- One-tap WhatsApp sharing
- Generic share options for other platforms
- Refresh functionality

## 🏗️ Architecture

### Project Structure
```
com.shilpakala.app/
├── ui/
│   ├── camera/
│   │   ├── CameraScreen.kt (CameraX integration)
│   │   ├── CameraOverlay.kt (Framing guide)
│   │   └── CameraViewModel.kt
│   ├── branding/
│   │   ├── BrandingScreen.kt (Input & preview)
│   │   └── BrandingViewModel.kt (Image processing)
│   ├── gallery/
│   │   ├── GalleryScreen.kt (Grid + sharing)
│   │   └── GalleryViewModel.kt
│   └── AppNavigation.kt (Navigation flow)
│
├── data/
│   └── model/
│       └── Artwork.kt (Data class)
│
├── utils/
│   ├── ImageProcessor.kt (Branding engine)
│   └── FileUtils.kt (Storage & sharing)
│
└── MainActivity.kt
```

### Tech Stack
- **UI Framework:** Jetpack Compose
- **Camera:** CameraX 1.3.0
- **Architecture:** MVVM with ViewModels
- **Navigation:** Jetpack Navigation Compose
- **Image Loading:** Coil
- **Permissions:** Accompanist Permissions
- **Target SDK:** 34

## 📦 Dependencies

```gradle
// Core
androidx.core:core-ktx:1.12.0
androidx.appcompat:appcompat:1.6.1

// Lifecycle & Compose
androidx.lifecycle:lifecycle-runtime-ktx:2.6.1
androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1
androidx.compose.ui:ui:1.5.0
androidx.compose.material3:material3:1.1.1
androidx.activity:activity-compose:1.7.2

// Camera
androidx.camera:camera-core:1.3.0
androidx.camera:camera-camera2:1.3.0
androidx.camera:camera-lifecycle:1.3.0
androidx.camera:camera-view:1.3.0

// Navigation & Permissions
androidx.navigation:navigation-compose:2.7.0
com.google.accompanist:accompanist-permissions:0.33.1-alpha

// Image Loading
io.coil-kt:coil-compose:2.4.0
```

## 🚀 Key Components

### ImageProcessor (Branding Engine)
```kotlin
fun addBranding(
    original: Bitmap,
    artisanName: String,
    woodType: String,
    price: String,
    heritageLabel: String
): Bitmap
```
Handles:
- Semi-transparent panel overlay
- Text rendering with proper positioning
- Gold accent borders
- Emoji integration

### FileUtils (Storage & Sharing)
```kotlin
fun saveImage(context: Context, bitmap: Bitmap): Uri?
fun shareToWhatsApp(context: Context, uri: Uri): Boolean
fun shareToGeneric(context: Context, uri: Uri): Boolean
```

### Heritage Label Generation
Auto-generates contextual labels based on wood type:
- Teak → "Handcrafted from premium teak wood in Karnataka..."
- Sheesham → "Artisanal sheesham masterpiece..."
- Rosewood → "Exquisite rosewood creation..."
- Bamboo → "Sustainable bamboo art..."
- And more...

## 📱 App Flow

```
Camera Screen
    ↓ (Capture)
Branding Screen
    ↓ (Save)
Gallery Screen
    ↓ (Share)
WhatsApp / Generic Share
```

## ✅ Required Permissions

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

## 🎨 UI/UX Highlights

- **Gold Theme:** #FFD700 for premium feel
- **Dark Mode Ready:** Color-adaptive designs
- **Smooth Transitions:** Navigation with state preservation
- **Visual Feedback:** Selection indicators, progress indicators
- **Empty State:** Helpful guidance when no images exist
- **Emoji Integration:** Modern, approachable interface

## 🔧 Building & Running

### Prerequisites
- Android Studio Flamingo or newer
- Gradle 8.1.0+
- Android SDK 34

### Build Steps
```bash
# Clone the repository
git clone <repo-url>
cd ShilpaKala

# Build the app
./gradlew build

# Run on device/emulator
./gradlew installDebug
```

## 📸 Screenshot Flow

1. **Camera** → Frame your product with overlay guide
2. **Branding** → Add artisan info and preview
3. **Gallery** → View and share all creations
4. **Share** → Send directly to WhatsApp or other apps

## 🎯 Design Philosophy

**Artisan-First Design**
- Minimal friction workflow
- Professional output from simple inputs
- Heritage-aware branding
- Instant social sharing

**Technical Excellence**
- Modern Android best practices
- Type-safe navigation
- Reactive state management
- Optimized image processing

## 🔮 Future Enhancements

- [ ] Background removal (AI-powered)
- [ ] Custom logo overlay
- [ ] Multi-language support (Kannada + English)
- [ ] Batch processing
- [ ] Cloud backup
- [ ] Real GenAI integration (Gemini/OpenAI)
- [ ] Templates library
- [ ] Video support

## 📝 License

This project is open-source. Feel free to modify and use for educational purposes.

## 👨‍💻 Developer Notes

### Key Implementation Details

1. **CameraX Integration:** Uses `ProcessCameraProvider` for lifecycle-aware camera management
2. **Image Capture:** Implements `ImageCapture.OnImageCapturedCallback` for real-time bitmap capture
3. **State Management:** ViewModels maintain UI state across configuration changes
4. **Navigation:** Jetpack Navigation Compose with state preservation
5. **Performance:** Bitmap operations are optimized with proper sizing before processing

### Testing on Device

Ensure device has:
- Android 5.0+ (API 24+)
- Camera hardware
- Sufficient storage for image saving

---

Built with ❤️ for Indian Artisans | Shilpa-Kala v1.0.0
=======
# Slipakala
>>>>>>> 73e1c7c3e3195900f3abad3e9cf425bf2ff91127
