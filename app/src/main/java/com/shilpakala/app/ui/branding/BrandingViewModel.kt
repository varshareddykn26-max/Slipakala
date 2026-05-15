package com.shilpakala.app.ui.branding

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shilpakala.app.data.model.Artwork
import com.shilpakala.app.utils.ImageProcessor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BrandingViewModel : ViewModel() {

    private val _currentArtwork = MutableStateFlow<Artwork?>(null)
    val currentArtwork: StateFlow<Artwork?> = _currentArtwork

    private val _isProcessing = MutableStateFlow(false)
    val isProcessing: StateFlow<Boolean> = _isProcessing

    fun setArtwork(
        originalBitmap: Bitmap,
        artisanName: String,
        woodType: String,
        price: String
    ) {
        viewModelScope.launch {
            _isProcessing.value = true
            
            val processed = withContext(Dispatchers.Default) {
                val heritageLabel = ImageProcessor.generateHeritageLabel(woodType)
                val processedBitmap = ImageProcessor.addBranding(
                    originalBitmap,
                    artisanName,
                    woodType,
                    price,
                    heritageLabel
                )
                
                Artwork(
                    originalBitmap = originalBitmap,
                    processedBitmap = processedBitmap,
                    artisanName = artisanName,
                    woodType = woodType,
                    price = price,
                    heritageLabel = heritageLabel,
                    isBranded = true
                )
            }

            _currentArtwork.value = processed
            _isProcessing.value = false
        }
    }

    fun updateBranding(
        artisanName: String,
        woodType: String,
        price: String
    ) {
        val original = _currentArtwork.value?.originalBitmap ?: return

        viewModelScope.launch {
            _isProcessing.value = true

            val updated = withContext(Dispatchers.Default) {
                val heritageLabel = ImageProcessor.generateHeritageLabel(woodType)
                val processedBitmap = ImageProcessor.addBranding(
                    original,
                    artisanName,
                    woodType,
                    price,
                    heritageLabel
                )

                _currentArtwork.value?.copy(
                    artisanName = artisanName,
                    woodType = woodType,
                    price = price,
                    heritageLabel = heritageLabel,
                    processedBitmap = processedBitmap
                )
            }

            _currentArtwork.value = updated
            _isProcessing.value = false
        }
    }

    fun getProcessedBitmap(): Bitmap? = _currentArtwork.value?.processedBitmap
}
