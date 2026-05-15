package com.shilpakala.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shilpakala.app.data.model.*

@Database(
    entities = [
        Product::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ShilpaKalaDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
