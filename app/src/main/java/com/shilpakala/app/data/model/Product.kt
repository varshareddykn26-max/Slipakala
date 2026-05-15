package com.shilpakala.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val productId: Int = 0,
    val productName: String,
    val category: String,
    val description: String,
    val price: Double,
    val stockQuantity: Int,
    val imagePath: String,
    val watermarkEnabled: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
