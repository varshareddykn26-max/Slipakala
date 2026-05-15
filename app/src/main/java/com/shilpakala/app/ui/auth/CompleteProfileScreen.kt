package com.shilpakala.app.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteProfileScreen(
    onSaveAndContinue: (ProfileData) -> Unit
) {
    var businessName by remember { mutableStateOf("") }
    var craftType by remember { mutableStateOf("Wooden Crafts") }
    var city by remember { mutableStateOf("") }
    var whatsappNumber by remember { mutableStateOf("+917975910936") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Complete Profile", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFFF9F0))
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Text(
                text = "Business Branding",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7D4427)
            )
            Text(
                text = "This information will be used to watermark and brand your product images.",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )
            
            ProfileTextField(value = businessName, onValueChange = { businessName = it }, label = "Artisan or business name")
            Spacer(modifier = Modifier.height(16.dp))
            ProfileTextField(value = craftType, onValueChange = { craftType = it }, label = "Craft type")
            Spacer(modifier = Modifier.height(16.dp))
            ProfileTextField(value = city, onValueChange = { city = it }, label = "City or village")
            Spacer(modifier = Modifier.height(16.dp))
            ProfileTextField(value = whatsappNumber, onValueChange = { whatsappNumber = it }, label = "WhatsApp number")
            Spacer(modifier = Modifier.height(16.dp))
            ProfileTextField(value = description, onValueChange = { description = it }, label = "Short craft description", minLines = 3)
            
            Spacer(modifier = Modifier.height(48.dp))
            
            Button(
                onClick = { 
                    onSaveAndContinue(ProfileData(businessName, craftType, city, whatsappNumber, description))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7D4427),
                    contentColor = Color.White
                ),
                enabled = businessName.isNotEmpty() && city.isNotEmpty()
            ) {
                Text("Save and Continue", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    minLines: Int = 1
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        minLines = minLines,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = Color(0xFF7D4427)
        )
    )
}

data class ProfileData(
    val businessName: String,
    val craftType: String,
    val city: String,
    val whatsappNumber: String,
    val description: String
)
