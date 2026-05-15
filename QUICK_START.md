# ShilpaKala - Quick Start Guide

## ⚡ 5-Minute Setup

### Step 1: Clone & Open
```bash
git clone https://github.com/yourrepo/ShilpaKala.git
cd ShilpaKala
# Open in Android Studio
```

### Step 2: Wait for Gradle Sync
- Android Studio syncs dependencies automatically
- First sync may take 2-3 minutes
- Check if any sync errors appear

### Step 3: Connect Device
```bash
# Check connection
adb devices

# You should see your device listed
```

### Step 4: Run App
```bash
# Using terminal
./gradlew installDebug

# OR use Android Studio
# Press Shift+F10 or click Run button
```

### Step 5: Grant Permissions
- App requests camera permission
- Tap "Allow" in the dialog
- Camera screen opens

---

## 📸 Basic Workflow

### 1. Take a Photo
```
Camera Screen
  ↓ [Tap Capture Button]
  ↓ [Grant Camera Permission if needed]
  ↓ [Photo captured]
  ↓ [Navigate to Branding]
```

### 2. Add Branding
```
Branding Screen
  ↓ [Enter Artisan Name]
  ↓ [Select Wood Type]
  ↓ [Enter Price]
  ↓ [Tap Preview Branding]
  ↓ [View branded image]
  ↓ [Tap Save Image]
```

### 3. Share
```
Gallery Screen
  ↓ [Select Image]
  ↓ [Tap Share via WhatsApp]
  ↓ [WhatsApp opens with image]
  ↓ [Send to contacts]
```

---

## 🎯 Key Screens

### Camera Screen
```
┌─────────────────────────────────────┐
│  Live Camera Preview               │
│  ┌───────────────────────────────┐ │
│  │ ┌─────────────────────────┐   │ │
│  │ │                         │   │ │
│  │ │   Framing Guide         │   │ │
│  │ │   (with gold corners)   │   │ │
│  │ │                         │   │ │
│  │ └─────────────────────────┘   │ │
│  └───────────────────────────────┘ │
│                                    │
│         [📸 Capture]               │
│         [Gallery]                  │
└─────────────────────────────────────┘
```

### Branding Screen
```
┌─────────────────────────────────────┐
│      ✨ Brand Your Artwork         │
├─────────────────────────────────────┤
│  [Preview Image Here]               │
│                                    │
│  Artisan Name: [Raj Kumar        ]│
│  Wood Type:    [Teak            ]│
│  Price:        [5000            ]│
│                                    │
│      [👁️ Preview Branding]        │
│      [💾 Save Image]               │
│      [Back]                        │
└─────────────────────────────────────┘
```

### Gallery Screen
```
┌─────────────────────────────────────┐
│         🖼️ My Artworks            │
├─────────────────────────────────────┤
│  ┌────┐  ┌────┐                   │
│  │ 📷 │  │ 📷 │                   │
│  └────┘  └────┘                   │
│  ┌────┐  ┌────┐                   │
│  │ 📷 │  │ 📷 │                   │
│  └────┘  └────┘                   │
│                                    │
│      [💬 Share via WhatsApp]       │
│      [🔗 Share]                    │
│      [📸 Capture New]              │
│      [🔄 Refresh]                  │
└─────────────────────────────────────┘
```

---

## 🔧 Troubleshooting

### Camera Not Opening
```
❌ Problem: "Camera permission denied"
✅ Solution: 
  - Go to Settings → Apps → ShilpaKala → Permissions
  - Enable Camera permission
  - Restart app
```

### Image Not Saving
```
❌ Problem: "Storage permission denied"
✅ Solution:
  - Grant storage permissions
  - Check device has free space (min 100MB)
  - For Android 11+: Enable "Allow all files" if needed
```

### App Crashing on Branding
```
❌ Problem: "App crashes when entering branding details"
✅ Solution:
  - Enter valid values (no empty fields)
  - Use alphanumeric characters
  - Restart the app
```

### WhatsApp Share Not Working
```
❌ Problem: "WhatsApp not found or share fails"
✅ Solution:
  - Install WhatsApp on device
  - Use generic share option instead
  - Check WhatsApp has permissions
```

---

## 📝 Development Tips

### Adding New Feature
```kotlin
// 1. Create new ViewModel
class MyFeatureViewModel : ViewModel() {
    private val _state = MutableStateFlow<State?>(null)
    val state: StateFlow<State?> = _state
}

// 2. Create UI Composable
@Composable
fun MyFeatureScreen(viewModel: MyFeatureViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    // UI code here
}

// 3. Add to Navigation
composable("myfeature") {
    MyFeatureScreen(viewModel())
}
```

### Testing ImageProcessor
```kotlin
// Create test image
val testBitmap = Bitmap.createBitmap(640, 480, Bitmap.Config.ARGB_8888)

// Apply branding
val branded = ImageProcessor.addBranding(
    testBitmap,
    "Test Artist",
    "Teak",
    "1000"
)

// Verify output
assert(branded.width == testBitmap.width)
assert(branded.height == testBitmap.height)
```

### Debugging Camera
```kotlin
// Add to MainActivity
Log.d("CameraX", "Camera ready: ${cameraProvider.isBound()}")
Log.d("CameraX", "Preview active: ${previewView.isAvailable()}")
```

---

## 🎓 Learning Path

### Week 1: Basics
- [ ] Run app and explore all screens
- [ ] Take some test images
- [ ] Try sharing to WhatsApp
- [ ] Read README.md & SETUP_GUIDE.md

### Week 2: Code Understanding
- [ ] Review CameraScreen.kt
- [ ] Understand ImageProcessor logic
- [ ] Trace data flow from camera to save
- [ ] Read ARCHITECTURE.md

### Week 3: Customization
- [ ] Modify branding colors
- [ ] Add new heritage labels
- [ ] Change overlay design
- [ ] Test with real images

### Week 4: Extension
- [ ] Add new feature (background blur, etc.)
- [ ] Write unit tests
- [ ] Optimize performance
- [ ] Submit as pull request

---

## 🚀 Performance Tips

### Camera Performance
- Keep preview resolution at device native
- Avoid heavy processing in preview callback
- Use Executors for camera operations

### Image Processing
- Resize large images before processing
- Use bitmap pools to reduce allocations
- Process images on background thread

### UI Performance
- Use LazyVerticalGrid for gallery
- Avoid recomposition of large hierarchies
- Use remember for state preservation

---

## 📱 Device Testing Checklist

Before deploying to production:

- [ ] Test on different Android versions (API 24, 29, 34)
- [ ] Test on different screen sizes (4", 5", 6", 7")
- [ ] Test camera on back & front
- [ ] Test low-light conditions
- [ ] Test with slow storage device
- [ ] Test with low memory device
- [ ] Test all permission scenarios

---

## 🔗 Useful Commands

```bash
# Build project
./gradlew build

# Run app
./gradlew installDebug

# View logs
adb logcat | grep ShilpaKala

# Connect device
adb devices

# Push file to device
adb push file.jpg /sdcard/DCIM/

# Pull file from device
adb pull /sdcard/Pictures/ShilpaKala/ .

# Clear app cache
adb shell pm clear com.shilpakala.app

# Uninstall app
adb uninstall com.shilpakala.app
```

---

## 📞 Getting Help

### Resources
- 📖 Official Android Docs: https://developer.android.com
- 🎥 YouTube: Android Development tutorials
- 💬 Stack Overflow: Tag with `android` and `jetpack-compose`
- 🐛 GitHub Issues: Report bugs or suggest features

### Community
- Android Developers Slack
- r/androiddev subreddit
- Local Android dev meetups

---

## ✨ Next Steps

1. **Run the App** - Complete the 5-minute setup
2. **Explore Features** - Use all three screens
3. **Read Code** - Understand architecture
4. **Customize** - Modify colors, text, features
5. **Deploy** - Build release APK for testing

---

**Happy Coding! 🎨**

*ShilpaKala brings artisan craftsmanship to the digital world*
