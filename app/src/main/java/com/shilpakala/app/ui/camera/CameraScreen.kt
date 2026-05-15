package com.shilpakala.app.ui.camera

import android.Manifest
import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import java.util.concurrent.Executor

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    onCapture: (Bitmap) -> Unit,
    onNavigateToGallery: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    val previewView = remember { PreviewView(context) }
    val imageCaptureRef = remember { mutableStateOf<ImageCapture?>(null) }

    LaunchedEffect(Unit) {
        if (cameraPermissionState.status != com.google.accompanist.permissions.PermissionStatus.Granted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (cameraPermissionState.status == com.google.accompanist.permissions.PermissionStatus.Granted) {
            AndroidView(
                factory = { previewView },
                modifier = Modifier.fillMaxSize()
            ) {
                val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()

                    val preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }

                    val imageCapture = ImageCapture.Builder()
                        .setTargetRotation(android.view.Surface.ROTATION_0)
                        .build()

                    imageCaptureRef.value = imageCapture

                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            preview,
                            imageCapture
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }, ContextCompat.getMainExecutor(context))
            }

            // Overlay
            CameraOverlay()

            // Bottom Controls
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.7f))
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        imageCaptureRef.value?.takePicture(
                            ContextCompat.getMainExecutor(context),
                            object : ImageCapture.OnImageCapturedCallback() {
                                override fun onCaptureSuccess(image: ImageProxy) {
                                    val bitmap = image.image?.let { yuvImage ->
                                        val nv21 = ByteArray(yuvImage.width * yuvImage.height * 3 / 2)
                                        val planes = yuvImage.planes
                                        var pixelStride = 0
                                        if (planes.isNotEmpty()) {
                                            pixelStride = planes[0].pixelStride
                                        }

                                        var uvPixelStride = 0
                                        if (planes.size > 1) {
                                            uvPixelStride = planes[1].pixelStride
                                        }

                                        var uvIndex = yuvImage.width * yuvImage.height
                                        var y = 0
                                        while (y < yuvImage.height) {
                                            var x = 0
                                            while (x < yuvImage.width) {
                                                nv21[y * yuvImage.width + x] = planes[0].buffer[y * yuvImage.width + x].toByte()
                                                if (y % 2 == 0 && uvIndex < nv21.size - 1) {
                                                    val rowPadding = planes[1].buffer.capacity() / (yuvImage.height / 2)
                                                    nv21[uvIndex] = planes[1].buffer[(y / 2) * rowPadding + x / 2].toByte()
                                                    nv21[uvIndex + 1] = planes[2].buffer[(y / 2) * rowPadding + x / 2].toByte()
                                                    uvIndex += 2
                                                }
                                                x++
                                            }
                                            y++
                                        }

                                        android.graphics.YuvImage(
                                            nv21,
                                            android.graphics.ImageFormat.NV21,
                                            yuvImage.width,
                                            yuvImage.height,
                                            null
                                        ).let { yuvImg ->
                                            val out = java.io.ByteArrayOutputStream()
                                            yuvImg.compressToJpeg(android.graphics.Rect(0, 0, yuvImage.width, yuvImage.height), 90, out)
                                            android.graphics.BitmapFactory.decodeByteArray(
                                                out.toByteArray(), 0, out.size()
                                            )
                                        }
                                    } ?: run {
                                        android.graphics.Bitmap.createBitmap(640, 480, android.graphics.Bitmap.Config.ARGB_8888)
                                    }

                                    if (bitmap != null) {
                                        onCapture(bitmap)
                                    }
                                    image.close()
                                }

                                override fun onError(exception: ImageCaptureException) {
                                    exception.printStackTrace()
                                }
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD700),
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "📸 Capture",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onNavigateToGallery,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Gallery",
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
