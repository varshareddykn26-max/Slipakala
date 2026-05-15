# ShilpaKala - API Reference

## 📦 Main Components

---

## 🎥 CameraScreen

### Composable Function
```kotlin
@Composable
fun CameraScreen(
    onCapture: (Bitmap) -> Unit,
    onNavigateToGallery: () -> Unit
)
```

### Parameters
- **onCapture**: Callback when user captures an image
- **onNavigateToGallery**: Navigate to gallery view

### Features
- CameraX live preview integration
- Professional framing overlay
- One-tap capture button
- Quick gallery access

---

## 🎨 BrandingScreen

### Composable Function
```kotlin
@Composable
fun BrandingScreen(
    originalBitmap: Bitmap,
    viewModel: BrandingViewModel,
    onSave: (Bitmap) -> Unit,
    onBack: () -> Unit
)
```

### Parameters
- **originalBitmap**: Original image from camera
- **viewModel**: Manages branding state
- **onSave**: Callback when user saves branded image
- **onBack**: Navigate back to camera

### Input Fields
- Artisan Name (Text input)
- Wood Type (Text input, auto-labels)
- Price (Numeric input)

### Outputs
- Real-time preview of branded image
- Processed bitmap ready to save

---

## 🖼️ GalleryScreen

### Composable Function
```kotlin
@Composable
fun GalleryScreen(
    viewModel: GalleryViewModel,
    onNavigateToCamera: () -> Unit,
    onRefresh: () -> Unit
)
```

### Parameters
- **viewModel**: Manages gallery state
- **onNavigateToCamera**: Navigate to camera
- **onRefresh**: Reload gallery images

### Features
- Grid view of all processed images
- Select images for sharing
- WhatsApp direct share
- Generic share options

---

## 🎬 CameraViewModel

### State Properties

```kotlin
// Captured image as bitmap
val capturedBitmap: StateFlow<Bitmap?>

// Camera ready status
val isCameraReady: StateFlow<Boolean>
```

### Methods

```kotlin
// Set the captured bitmap
fun setCaptureBitmap(bitmap: Bitmap): Unit

// Clear the captured bitmap
fun clearCaptureBitmap(): Unit

// Set camera ready status
fun setCameraReady(ready: Boolean): Unit
```

---

## 🎨 BrandingViewModel

### State Properties

```kotlin
// Current artwork being processed
val currentArtwork: StateFlow<Artwork?>

// Processing state
val isProcessing: StateFlow<Boolean>
```

### Methods

```kotlin
// Process and brand the image
fun setArtwork(
    originalBitmap: Bitmap,
    artisanName: String,
    woodType: String,
    price: String
): Unit

// Update branding with new values
fun updateBranding(
    artisanName: String,
    woodType: String,
    price: String
): Unit

// Get the processed bitmap
fun getProcessedBitmap(): Bitmap?
```

---

## 🖼️ GalleryViewModel

### State Properties

```kotlin
// List of image URIs
val images: StateFlow<List<Uri>>
```

### Methods

```kotlin
// Set all images
fun setImages(newImages: List<Uri>): Unit

// Add single image
fun addImage(uri: Uri): Unit
```

---

## 🖼️ Artwork Data Model

```kotlin
data class Artwork(
    val id: String = "",
    val originalBitmap: Bitmap? = null,
    val processedBitmap: Bitmap? = null,
    val artisanName: String = "",
    val woodType: String = "",
    val price: String = "",
    val heritageLabel: String = "",
    val savedUri: Uri? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val isBranded: Boolean = false
)
```

### Properties
- **id**: Unique identifier
- **originalBitmap**: Original captured image
- **processedBitmap**: Branded final image
- **artisanName**: Artist name
- **woodType**: Type of wood used
- **price**: Product price
- **heritageLabel**: Auto-generated heritage description
- **savedUri**: MediaStore URI reference
- **createdAt**: Creation timestamp
- **isBranded**: Processing status

---

## 🎨 ImageProcessor Object

### Static Methods

#### addBranding()
```kotlin
fun addBranding(
    original: Bitmap,
    artisanName: String,
    woodType: String,
    price: String,
    heritageLabel: String = ""
): Bitmap
```

**Returns:** Branded bitmap with overlay panel

**Processing:**
- Adds semi-transparent panel at bottom
- Gold accent border
- Artisan info text
- Heritage label (optional)

---

#### addOverlayGuide()
```kotlin
fun addOverlayGuide(bitmap: Bitmap): Bitmap
```

**Returns:** Bitmap with framing overlay

**Features:**
- White frame rectangle
- Gold corner markers
- Semi-transparent overlay outside frame

---

#### resizeBitmap()
```kotlin
fun resizeBitmap(
    bitmap: Bitmap,
    maxWidth: Int = 1080,
    maxHeight: Int = 1920
): Bitmap
```

**Parameters:**
- **bitmap**: Image to resize
- **maxWidth**: Maximum width in pixels
- **maxHeight**: Maximum height in pixels

**Returns:** Properly scaled bitmap

---

#### generateHeritageLabel()
```kotlin
fun generateHeritageLabel(woodType: String): String
```

**Parameters:**
- **woodType**: Type of wood (case-insensitive)

**Returns:** Contextual heritage label

**Supported Types:**
- Teak
- Sheesham
- Rosewood
- Bamboo
- Walnut
- Sal
- Deodar

**Default:** Generic label if type not found

---

## 📁 FileUtils Object

### Static Methods

#### saveImage()
```kotlin
fun saveImage(
    context: Context,
    bitmap: Bitmap
): Uri?
```

**Returns:** MediaStore URI if successful, null otherwise

**Process:**
- Saves to Pictures/ShilpaKala directory
- JPEG compression at 95% quality
- Creates timestamp filename
- Compatible with scoped storage

---

#### shareToWhatsApp()
```kotlin
fun shareToWhatsApp(
    context: Context,
    uri: Uri
): Boolean
```

**Returns:** True if intent started, false otherwise

**Action:** Launches WhatsApp with image attachment

---

#### shareToGeneric()
```kotlin
fun shareToGeneric(
    context: Context,
    uri: Uri
): Boolean
```

**Returns:** True if intent started, false otherwise

**Action:** Shows share chooser for all apps

---

#### getAllImages()
```kotlin
fun getAllImages(context: Context): List<Uri>
```

**Returns:** List of saved image URIs

**Queries:** MediaStore for ShilpaKala folder images

**Sorting:** By date modified (newest first)

---

## 🎬 Overlay Components

### CameraOverlay()
```kotlin
@Composable
fun CameraOverlay()
```

**Features:**
- Centered frame rectangle
- White stroke outline
- Gold corner decorations
- Semi-transparent overlay outside frame
- Device-adaptive sizing

---

## 🧭 Navigation Structure

### Routes
```
camera
├─ onCapture → branding
└─ onNavigateToGallery → gallery

branding
├─ onSave → gallery
└─ onBack → camera

gallery
├─ onNavigateToCamera → camera
└─ onRefresh → reload gallery
```

---

## 📱 Permissions Required

```xml
android.permission.CAMERA
android.permission.READ_EXTERNAL_STORAGE
android.permission.WRITE_EXTERNAL_STORAGE
```

---

## 🎯 Usage Examples

### Capture Image
```kotlin
CameraScreen(
    onCapture = { bitmap ->
        capturedBitmap = bitmap
        navController.navigate("branding")
    },
    onNavigateToGallery = {
        navController.navigate("gallery")
    }
)
```

### Brand Image
```kotlin
val viewModel: BrandingViewModel = viewModel()
viewModel.setArtwork(
    bitmap,
    "Raj Kumar",
    "Teak",
    "5000"
)
```

### Save to Gallery
```kotlin
FileUtils.saveImage(context, processedBitmap)?.let { uri ->
    FileUtils.shareToWhatsApp(context, uri)
}
```

---

## 🔧 Configuration Constants

```kotlin
// Image compression
JPEG_QUALITY = 95

// Default sizes
MAX_WIDTH = 1080
MAX_HEIGHT = 1920

// Branding panel
PANEL_HEIGHT = 280f
GOLD_COLOR = "#D4AF37"
DARK_COLOR = "#2C2C2C"

// Text sizes
TITLE_SIZE = 48f
TEXT_SIZE = 36f
LABEL_SIZE = 28f
```

---

## ⚙️ System Requirements

### Minimum
- Android 5.0+ (API 24)
- 512MB RAM
- Camera hardware

### Recommended
- Android 10+ (API 29)
- 2GB RAM
- Modern camera sensor

---

## 🔐 Storage Access

### Scoped Storage (Android 11+)
- No root directory access
- Uses MediaStore API
- Automatic cleanup on uninstall

### Permissions
- READ_EXTERNAL_STORAGE (querying)
- WRITE_EXTERNAL_STORAGE (saving)

---

**Last Updated:** May 2026
**API Version:** 1.0
