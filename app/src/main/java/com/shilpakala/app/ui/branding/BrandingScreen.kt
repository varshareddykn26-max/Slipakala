package com.shilpakala.app.ui.branding

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.shilpakala.app.ui.auth.ProfileData

@Composable
fun BrandingScreen(
    originalBitmap: Bitmap,
    viewModel: BrandingViewModel,
    userProfile: ProfileData? = null,
    onSave: (Bitmap) -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val artwork by viewModel.currentArtwork.collectAsStateWithLifecycle()
    val isProcessing by viewModel.isProcessing.collectAsStateWithLifecycle()

    var artisanName by remember { mutableStateOf(userProfile?.businessName ?: "") }
    var woodType by remember { mutableStateOf(userProfile?.craftType ?: "Teak") }
    var price by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2C2C2C))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "✨ Brand Your Artwork",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD700)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Preview
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.LightGray, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (artwork?.processedBitmap != null) {
                Image(
                    bitmap = artwork!!.processedBitmap!!.asImageBitmap(),
                    contentDescription = "Branded Preview",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    bitmap = originalBitmap.asImageBitmap(),
                    contentDescription = "Original Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Input Fields
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Artisan Information",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2C2C2C)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = artisanName,
                onValueChange = { artisanName = it },
                label = { Text("Artisan Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = woodType,
                onValueChange = { woodType = it },
                label = { Text("Wood Type (e.g., Teak, Sheesham)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price (₹)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Preview Button
            Button(
                onClick = {
                    if (artisanName.isNotEmpty() && woodType.isNotEmpty() && price.isNotEmpty()) {
                        viewModel.setArtwork(originalBitmap, artisanName, woodType, price)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD700),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = !isProcessing
            ) {
                if (isProcessing) {
                    CircularProgressIndicator(
                        color = Color.Black,
                        modifier = Modifier.width(24.dp)
                    )
                } else {
                    Text("👁️ Preview Branding", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Save Button
            Button(
                onClick = {
                    artwork?.processedBitmap?.let { onSave(it) }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = artwork?.processedBitmap != null && !isProcessing
            ) {
                Text("💾 Save Image", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Back Button
            Button(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Back", fontWeight = FontWeight.Bold)
            }
        }
    }
}