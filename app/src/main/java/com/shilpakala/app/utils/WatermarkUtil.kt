package com.shilpakala.app.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Typeface
import android.text.TextPaint

object WatermarkUtil {
    
    fun addTextWatermark(
        originalBitmap: Bitmap,
        watermarkText: String,
        opacity: Float = 0.5f,
        position: String = "bottom_right"
    ): Bitmap {
        val outputBitmap = Bitmap.createBitmap(
            originalBitmap.width,
            originalBitmap.height,
            Bitmap.Config.ARGB_8888
        )
        
        val canvas = Canvas(outputBitmap)
        canvas.drawBitmap(originalBitmap, 0f, 0f, null)
        
        val textPaint = TextPaint().apply {
            color = Color.WHITE
            textSize = 60f
            typeface = Typeface.DEFAULT_BOLD
            alpha = (255 * opacity).toInt()
        }
        
        val metrics = textPaint.fontMetrics
        val textHeight = metrics.descent - metrics.ascent
        
        val (x, y) = getPositionCoordinates(
            canvas.width.toFloat(),
            canvas.height.toFloat(),
            textPaint.measureText(watermarkText),
            textHeight,
            position
        )
        
        // Add shadow effect
        val shadowPaint = TextPaint(textPaint).apply {
            color = Color.BLACK
            alpha = ((255 * opacity * 0.5).toInt())
        }
        canvas.drawText(watermarkText, x + 2, y + 2, shadowPaint)
        
        // Draw main text
        canvas.drawText(watermarkText, x, y, textPaint)
        
        return outputBitmap
    }
    
    fun addLogoWatermark(
        originalBitmap: Bitmap,
        logoBitmap: Bitmap,
        opacity: Float = 0.5f,
        position: String = "bottom_right"
    ): Bitmap {
        val outputBitmap = Bitmap.createBitmap(
            originalBitmap.width,
            originalBitmap.height,
            Bitmap.Config.ARGB_8888
        )
        
        val canvas = Canvas(outputBitmap)
        canvas.drawBitmap(originalBitmap, 0f, 0f, null)
        
        // Scale logo to be 15% of image width
        val logoWidth = originalBitmap.width * 0.15f
        val logoHeight = (logoWidth * logoBitmap.height) / logoBitmap.width
        
        val scaledLogo = Bitmap.createScaledBitmap(
            logoBitmap,
            logoWidth.toInt(),
            logoHeight.toInt(),
            true
        )
        
        val (x, y) = getPositionCoordinates(
            canvas.width.toFloat(),
            canvas.height.toFloat(),
            logoWidth,
            logoHeight,
            position
        )
        
        val paint = Paint().apply {
            alpha = (255 * opacity).toInt()
        }
        
        canvas.drawBitmap(scaledLogo, x, y, paint)
        
        return outputBitmap
    }
    
    private fun getPositionCoordinates(
        canvasWidth: Float,
        canvasHeight: Float,
        elementWidth: Float,
        elementHeight: Float,
        position: String
    ): Pair<Float, Float> {
        val margin = 20f
        
        return when (position) {
            "bottom_right" -> {
                Pair(
                    canvasWidth - elementWidth - margin,
                    canvasHeight - elementHeight - margin
                )
            }
            "bottom_left" -> {
                Pair(margin, canvasHeight - elementHeight - margin)
            }
            "top_right" -> {
                Pair(canvasWidth - elementWidth - margin, margin + elementHeight)
            }
            "top_left" -> {
                Pair(margin, margin + elementHeight)
            }
            "center" -> {
                Pair(
                    (canvasWidth - elementWidth) / 2,
                    (canvasHeight - elementHeight) / 2
                )
            }
            else -> {
                Pair(
                    canvasWidth - elementWidth - margin,
                    canvasHeight - elementHeight - margin
                )
            }
        }
    }
}
