# Architecture Deep Dive - ShilpaKala

## 📐 System Architecture

### Layers

```
┌─────────────────────────────────────────┐
│           UI Layer (Compose)            │
│  Camera / Branding / Gallery Screens    │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│        ViewModel Layer (MVVM)           │
│  State Management & Business Logic      │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│        Utility Layer                    │
│  ImageProcessor / FileUtils             │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│        Android Framework                │
│  CameraX / MediaStore / Intent          │
└─────────────────────────────────────────┘
```

## 🧩 Component Interaction

### Camera Flow
```
User Opens App
    ↓
CameraScreen Requests Permission
    ↓
CameraViewModel Initialize
    ↓
CameraX Binds to Lifecycle
    ↓
PreviewView Shows Live Stream
    ↓
CameraOverlay Renders Framing Guide
    ↓
User Captures Image
    ↓
ImageCapture Returns Bitmap
    ↓
Navigate to Branding Screen
```

### Branding Flow
```
BrandingScreen Opens with Original Bitmap
    ↓
BrandingViewModel Initializes
    ↓
User Enters: Name, Wood Type, Price
    ↓
User Taps "Preview Branding"
    ↓
ImageProcessor.addBranding() Called
    ↓
Heritage Label Generated Automatically
    ↓
Processed Bitmap Displayed
    ↓
User Reviews & Taps "Save Image"
    ↓
FileUtils.saveImage() Invoked
    ↓
Image Saved to MediaStore
    ↓
Navigate to Gallery
```

### Gallery Flow
```
GalleryScreen Opens
    ↓
GalleryViewModel Fetches Images
    ↓
FileUtils.getAllImages() Queries MediaStore
    ↓
Images Display in LazyVerticalGrid
    ↓
User Selects Image
    ↓
User Taps "Share via WhatsApp"
    ↓
FileUtils.shareToWhatsApp() Creates Intent
    ↓
WhatsApp App Opens with Image
```

## 🔄 State Management

### ViewModel Pattern

```kotlin
class CameraViewModel : ViewModel() {
    private val _capturedBitmap = MutableStateFlow<Bitmap?>(null)
    val capturedBitmap: StateFlow<Bitmap?> = _capturedBitmap
    
    fun setCaptureBitmap(bitmap: Bitmap) {
        _capturedBitmap.value = bitmap  // Updates UI reactively
    }
}
```

**Benefits:**
- Survives configuration changes
- Lifecycle-aware
- Thread-safe state updates
- Easy to test

### Reactive Flow

```
User Action → ViewModel Update
    ↓
StateFlow Emits New Value
    ↓
Compose Recomposes with New State
    ↓
UI Updates Automatically
```

## 🎨 Image Processing Pipeline

### Bitmap Transformation Steps

1. **Capture**
   ```kotlin
   ImageProxy → YUV to RGB Conversion → Bitmap
   ```

2. **Process**
   ```kotlin
   Bitmap → Canvas Drawing → New Bitmap with Branding
   ```

3. **Save**
   ```kotlin
   Bitmap → JPEG Compression → MediaStore URI
   ```

## 📦 Data Flow Diagram

```
Camera Device
    ↓
CameraX Pipeline
    ↓
ImageCapture Callback
    ↓
YUV→RGB Conversion
    ↓
Android Bitmap
    ↓
Canvas + Paint Operations
    ↓
Branded Bitmap
    ↓
MediaStore Save
    ↓
Shareable URI
    ↓
Intent to WhatsApp/Other Apps
```

## 🔌 Dependency Injection (Current)

Currently, **manual ViewModels** are created:

```kotlin
@Composable
fun BrandingScreen() {
    val viewModel: BrandingViewModel = viewModel()  // Hilt could manage this
    // ...
}
```

**Future Enhancement:** Use Hilt for dependency management

## 🎯 Key Design Patterns

### 1. MVVM (Model-View-ViewModel)
- **Model:** Artwork data class
- **View:** Compose UI functions
- **ViewModel:** State & business logic

### 2. Repository Pattern (Optional)
Could be added for data persistence:

```kotlin
class ArtworkRepository {
    fun saveArtwork(artwork: Artwork) { /*...*/ }
    fun getAllArtworks(): Flow<List<Artwork>> { /*...*/ }
}
```

### 3. Strategy Pattern
ImageProcessor uses different strategies:
- `addBranding()` - Standard branding
- `addOverlayGuide()` - Frame overlay
- `generateHeritageLabel()` - Smart labeling

## 🔒 Thread Safety

### Main Thread Operations
- UI updates (Compose)
- Camera operations
- MediaStore queries

### Background Operations
- Image compression (handled by Coil)
- Large bitmap operations (could use Coroutines)

### Future Enhancement:
```kotlin
viewModel.viewModelScope.launch {
    val processed = withContext(Dispatchers.Default) {
        ImageProcessor.addBranding(...)
    }
    _processedBitmap.value = processed
}
```

## 📱 Lifecycle Integration

```
Activity Created
    ↓
Compose Sets Content
    ↓
Screens Rendered
    ↓
CameraX Binds to Lifecycle
    ↓
Permissions Requested
    ↓
Camera Starts
    ↓
User Interacts
    ↓
Activity Paused (Camera stops)
    ↓
Activity Destroyed
    ↓
ViewModels Cleared
```

## 🎬 Transition Architecture

### Navigation Graph

```
camera ←→ branding
  ↓        ↓
gallery ←→ camera
```

**Jetpack Navigation Benefits:**
- Type-safe argument passing
- Back stack management
- State preservation
- Deep linking support

## 🧪 Testing Architecture

### Unit Tests (ImageProcessor)
```kotlin
@Test
fun testAddBranding() {
    val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
    val result = ImageProcessor.addBranding(bitmap, "Test", "Teak", "100")
    assert(result.height == bitmap.height)
}
```

### Instrumented Tests (Camera)
```kotlin
@Test
fun testCameraPermission() {
    // Test permission request flow
}
```

## 🚀 Performance Considerations

### Memory Management
- Bitmap resizing before processing
- Proper resource cleanup
- Image compression (JPEG 95%)

### Camera Performance
- 60 FPS live preview
- Efficient surface rendering
- Lifecycle-aware binding

### Disk I/O
- Async save operations
- Efficient MediaStore queries
- Lazy image loading (Coil)

## 🔐 Security Architecture

### Permissions
```xml
CAMERA - Request at runtime
READ_EXTERNAL_STORAGE - Scoped storage compliant
WRITE_EXTERNAL_STORAGE - MediaStore API
```

### Data Storage
- No sensitive data stored locally
- Images saved to Pictures/ShilpaKala
- No network communication (can be added)

## 🔄 Extension Points

### Add ML Kit Features
```kotlin
object MLProcessor {
    fun removeBackground(bitmap: Bitmap): Bitmap { /*...*/ }
    fun enhanceImage(bitmap: Bitmap): Bitmap { /*...*/ }
}
```

### Add Cloud Storage
```kotlin
interface StorageRepository {
    suspend fun uploadImage(bitmap: Bitmap, metadata: Metadata): String
    suspend fun downloadImage(id: String): Bitmap
}
```

### Add Analytics
```kotlin
object Analytics {
    fun logImageCapture() { /*...*/ }
    fun logShare(platform: String) { /*...*/ }
}
```

---

**Last Updated:** May 2026
