package com.shilpakala.app.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import kotlin.math.roundToInt

object ImageProcessor {

    fun addBranding(
        original: Bitmap,
        artisanName: String,
        woodType: String,
        price: String,
        heritageLabel: String = ""
    ): Bitmap {
        val result = original.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(result)

        // Draw semi-transparent background panel at bottom
        val rectPaint = Paint().apply {
            color = Color.BLACK
            alpha = 180
            isAntiAlias = true
        }

        val panelHeight = 280f
        val panelRect = RectF(
            0f,
            result.height - panelHeight,
            result.width.toFloat(),
            result.height.toFloat()
        )
        canvas.drawRect(panelRect, rectPaint)

        // Add decorative border
        val borderPaint = Paint().apply {
            color = Color.parseColor("#D4AF37") // Gold color
            strokeWidth = 4f
            style = Paint.Style.STROKE
            isAntiAlias = true
        }
        canvas.drawLine(0f, result.height - panelHeight, result.width.toFloat(), result.height - panelHeight, borderPaint)

        // Text paint setup
        val titlePaint = Paint().apply {
            color = Color.WHITE
            textSize = 48f
            isAntiAlias = true
            isFakeBoldText = true
        }

        val textPaint = Paint().apply {
            color = Color.parseColor("#E0E0E0")
            textSize = 36f
            isAntiAlias = true
        }

        val labelPaint = Paint().apply {
            color = Color.parseColor("#B8860B") // Dark goldenrod
            textSize = 28f
            isAntiAlias = true
        }

        val padding = 40f
        var yOffset = result.height - panelHeight + 60f

        // Draw artisan name
        canvas.drawText("✨ By $artisanName", padding, yOffset, titlePaint)
        yOffset += 55f

        // Draw wood type
        canvas.drawText("Wood: $woodType", padding, yOffset, textPaint)
        yOffset += 50f

        // Draw price
        canvas.drawText("₹ $price", padding, yOffset, textPaint)
        yOffset += 50f

        // Draw heritage label if provided
        if (heritageLabel.isNotEmpty()) {
            canvas.drawText("🌿 $heritageLabel", padding, yOffset, labelPaint)
        }

        return result
    }

    fun addOverlayGuide(bitmap: Bitmap): Bitmap {
        val result = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(result)

        val rectWidth = bitmap.width * 0.75f
        val rectHeight = bitmap.height * 0.55f
        val left = (bitmap.width - rectWidth) / 2
        val top = (bitmap.height - rectHeight) / 2

        val strokePaint = Paint().apply {
            color = Color.WHITE
            strokeWidth = 6f
            style = Paint.Style.STROKE
            isAntiAlias = true
        }

        canvas.drawRect(left, top, left + rectWidth, top + rectHeight, strokePaint)

        // Draw corner markers
        val cornerSize = 30f
        val cornerPaint = Paint().apply {
            color = Color.parseColor("#FFD700") // Gold
            strokeWidth = 8f
            style = Paint.Style.STROKE
            isAntiAlias = true
        }

        // Top-left
        canvas.drawLine(left, top, left + cornerSize, top, cornerPaint)
        canvas.drawLine(left, top, left, top + cornerSize, cornerPaint)

        // Top-right
        canvas.drawLine(left + rectWidth, top, left + rectWidth - cornerSize, top, cornerPaint)
        canvas.drawLine(left + rectWidth, top, left + rectWidth, top + cornerSize, cornerPaint)

        // Bottom-left
        canvas.drawLine(left, top + rectHeight, left + cornerSize, top + rectHeight, cornerPaint)
        canvas.drawLine(left, top + rectHeight, left, top + rectHeight - cornerSize, cornerPaint)

        // Bottom-right
        canvas.drawLine(left + rectWidth, top + rectHeight, left + rectWidth - cornerSize, top + rectHeight, cornerPaint)
        canvas.drawLine(left + rectWidth, top + rectHeight, left + rectWidth, top + rectHeight - cornerSize, cornerPaint)

        return result
    }

    fun resizeBitmap(bitmap: Bitmap, maxWidth: Int = 1080, maxHeight: Int = 1920): Bitmap {
        val ratio = bitmap.width.toFloat() / bitmap.height.toFloat()
        val newWidth: Int
        val newHeight: Int

        if (bitmap.width > bitmap.height) {
            newWidth = maxWidth
            newHeight = (newWidth / ratio).roundToInt()
        } else {
            newHeight = maxHeight
            newWidth = (newHeight * ratio).roundToInt()
        }

        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
    }

    fun generateHeritageLabel(woodType: String): String {
        val labels = mapOf(
            "teak" to "Handcrafted from premium teak wood in Karnataka, preserving ancestral woodcraft traditions",
            "sheesham" to "Artisanal sheesham masterpiece, honoring centuries of Indian carpentry heritage",
            "rosewood" to "Exquisite rosewood creation, following time-honored craftsmanship from the Western Ghats",
            "bamboo" to "Sustainable bamboo art, celebrating eco-conscious traditional artistry",
            "walnut" to "Premium walnut craftsmanship, blending modern design with classical techniques",
            "sal" to "Authentic sal wood artifact, rooted in rural Indian artisan traditions",
            "deodar" to "Himalayan deodar masterwork, inspired by ancient temple woodcraft"
        )

        return labels[woodType.lowercase()] 
            ?: "Handcrafted from premium $woodType wood, celebrating Indian artisan heritage"
    }
}
