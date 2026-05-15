package com.shilpakala.app.ui.camera

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CameraViewModel : ViewModel() {

    private val _capturedBitmap = MutableStateFlow<Bitmap?>(null)
    val capturedBitmap: StateFlow<Bitmap?> = _capturedBitmap

    private val _isCameraReady = MutableStateFlow(false)
    val isCameraReady: StateFlow<Boolean> = _isCameraReady

    fun setCaptureBitmap(bitmap: Bitmap) {
        _capturedBitmap.value = bitmap
    }

    fun clearCaptureBitmap() {
        _capturedBitmap.value = null
    }

    fun setCameraReady(ready: Boolean) {
        _isCameraReady.value = ready
    }
}
