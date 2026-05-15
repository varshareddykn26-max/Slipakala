package com.shilpakala.app.ui.gallery

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GalleryViewModel : ViewModel() {

    private val _images = MutableStateFlow<List<Uri>>(emptyList())
    val images: StateFlow<List<Uri>> = _images

    fun setImages(newImages: List<Uri>) {
        _images.value = newImages
    }

    fun addImage(uri: Uri) {
        _images.value = _images.value + uri
    }
}
