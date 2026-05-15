package com.shilpakala.app.data.model

import android.graphics.Bitmap
import android.net.Uri
import java.time.LocalDateTime

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