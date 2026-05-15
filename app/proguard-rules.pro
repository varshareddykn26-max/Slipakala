proguard-rules.pro

# Keep Compose related classes
-keep class androidx.compose.** { *; }
-keepclasseswithmembers class androidx.compose.** { *; }

# Keep CameraX related classes
-keep class androidx.camera.** { *; }
-keepclasseswithmembers class androidx.camera.** { *; }

# Keep ViewModel classes
-keep class * extends androidx.lifecycle.ViewModel { *; }

# Keep Navigation classes
-keep class androidx.navigation.** { *; }
-keepclasseswithmembers class androidx.navigation.** { *; }

# Preserve line numbers for debugging
-keepattributes SourceFile,LineNumberTable

# Keep the main activity
-keep class com.shilpakala.app.MainActivity { *; }
