package com.shilpakala.app.ui.camera

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun CameraOverlay() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val rectWidth = size.width * 0.75f
        val rectHeight = size.height * 0.55f
        val left = (size.width - rectWidth) / 2
        val top = (size.height - rectHeight) / 2

        // Main frame rectangle
        drawRect(
            color = Color.White,
            topLeft = Offset(left, top),
            size = Size(rectWidth, rectHeight),
            style = Stroke(width = 6f)
        )

        // Corner decorations
        val cornerSize = 40f
        val goldColor = Color(0xFFD700) // Gold

        // Top-left corner
        drawLine(
            color = goldColor,
            start = Offset(left, top),
            end = Offset(left + cornerSize, top),
            strokeWidth = 8f
        )
        drawLine(
            color = goldColor,
            start = Offset(left, top),
            end = Offset(left, top + cornerSize),
            strokeWidth = 8f
        )

        // Top-right corner
        drawLine(
            color = goldColor,
            start = Offset(left + rectWidth, top),
            end = Offset(left + rectWidth - cornerSize, top),
            strokeWidth = 8f
        )
        drawLine(
            color = goldColor,
            start = Offset(left + rectWidth, top),
            end = Offset(left + rectWidth, top + cornerSize),
            strokeWidth = 8f
        )

        // Bottom-left corner
        drawLine(
            color = goldColor,
            start = Offset(left, top + rectHeight),
            end = Offset(left + cornerSize, top + rectHeight),
            strokeWidth = 8f
        )
        drawLine(
            color = goldColor,
            start = Offset(left, top + rectHeight),
            end = Offset(left, top + rectHeight - cornerSize),
            strokeWidth = 8f
        )

        // Bottom-right corner
        drawLine(
            color = goldColor,
            start = Offset(left + rectWidth, top + rectHeight),
            end = Offset(left + rectWidth - cornerSize, top + rectHeight),
            strokeWidth = 8f
        )
        drawLine(
            color = goldColor,
            start = Offset(left + rectWidth, top + rectHeight),
            end = Offset(left + rectWidth, top + rectHeight - cornerSize),
            strokeWidth = 8f
        )

        // Semi-transparent overlay outside frame
        drawRect(
            color = Color.Black.copy(alpha = 0.5f),
            topLeft = Offset(0f, 0f),
            size = Size(left, size.height)
        )
        drawRect(
            color = Color.Black.copy(alpha = 0.5f),
            topLeft = Offset(left + rectWidth, 0f),
            size = Size(size.width - (left + rectWidth), size.height)
        )
        drawRect(
            color = Color.Black.copy(alpha = 0.5f),
            topLeft = Offset(left, 0f),
            size = Size(rectWidth, top)
        )
        drawRect(
            color = Color.Black.copy(alpha = 0.5f),
            topLeft = Offset(left, top + rectHeight),
            size = Size(rectWidth, size.height - (top + rectHeight))
        )
    }
}
