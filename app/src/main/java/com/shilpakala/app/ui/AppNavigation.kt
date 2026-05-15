package com.shilpakala.app.ui

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shilpakala.app.ui.auth.*
import com.shilpakala.app.ui.branding.BrandingScreen
import com.shilpakala.app.ui.branding.BrandingViewModel
import com.shilpakala.app.ui.camera.CameraScreen
import com.shilpakala.app.ui.gallery.GalleryScreen
import com.shilpakala.app.ui.gallery.GalleryViewModel
import com.shilpakala.app.ui.home.HomeScreen
import com.shilpakala.app.utils.FileUtils

@Composable
fun ShilpaKalaApp(context: Context) {
    val navController = rememberNavController()

    var capturedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var shouldRefreshGallery by remember { mutableStateOf(false) }

    var userProfile by remember { mutableStateOf<ProfileData?>(null) }
    var selectedRole by remember { mutableStateOf<String?>(null) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.fillMaxSize(),
            enterTransition = { fadeIn(animationSpec = tween(300)) },
            exitTransition = { fadeOut(animationSpec = tween(300)) }
        ) {
            composable("login") {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate("welcome") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onGoogleLogin = {
                        navController.navigate("welcome") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onSignUp = {
                        navController.navigate("welcome")
                    }
                )
            }

            composable("welcome") {
                WelcomeScreen(
                    onSellerClick = {
                        selectedRole = "Vendor"
                        navController.navigate("verify_phone")
                    },
                    onBuyerClick = {
                        selectedRole = "Buyer"
                        navController.navigate("verify_phone")
                    }
                )
            }

            composable("verify_phone") {
                VerifyPhoneScreen(
                    role = selectedRole ?: "User",
                    onBack = { navController.popBackStack() },
                    onSendOtp = { phoneNumber ->
                        navController.navigate("complete_profile")
                    }
                )
            }

            composable("complete_profile") {
                CompleteProfileScreen(
                    onSaveAndContinue = { profileData ->
                        userProfile = profileData
                        navController.navigate("home") {
                            popUpTo("welcome") { inclusive = true }
                        }
                    }
                )
            }

            composable("home") {
                HomeScreen(
                    userName = userProfile?.businessName ?: "skanda",
                    craftType = userProfile?.craftType ?: "Wooden Crafts",
                    city = userProfile?.city ?: "Bangalore",
                    onCaptureNew = { navController.navigate("camera") },
                    onViewProducts = { navController.navigate("gallery") },
                    onLogout = {
                        userProfile = null
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                )
            }

            composable("camera") {
                CameraScreen(
                    onCapture = { bitmap ->
                        capturedBitmap = bitmap
                        navController.navigate("branding")
                    },
                    onNavigateToGallery = {
                        navController.navigate("gallery")
                    }
                )
            }

            composable("branding") {
                capturedBitmap?.let { bitmap ->
                    val brandingViewModel: BrandingViewModel = viewModel()
                    BrandingScreen(
                        originalBitmap = bitmap,
                        viewModel = brandingViewModel,
                        userProfile = userProfile,
                        onSave = { processedBitmap ->
                            FileUtils.saveImage(context, processedBitmap)?.let {
                                shouldRefreshGallery = !shouldRefreshGallery
                                navController.popBackStack()
                                navController.navigate("gallery")
                            }
                        },
                        onBack = {
                            capturedBitmap = null
                            navController.popBackStack()
                        }
                    )
                }
            }

            composable("gallery") {
                val galleryViewModel: GalleryViewModel = viewModel()
                
                LaunchedEffect(shouldRefreshGallery) {
                    val images = FileUtils.getAllImages(context)
                    galleryViewModel.setImages(images)
                }

                GalleryScreen(
                    viewModel = galleryViewModel,
                    onNavigateToCamera = {
                        navController.navigate("camera") {
                            popUpTo("gallery") { saveState = true }
                            restoreState = true
                        }
                    },
                    onRefresh = {
                        val images = FileUtils.getAllImages(context)
                        galleryViewModel.setImages(images)
                    }
                )
            }
        }
    }
}
