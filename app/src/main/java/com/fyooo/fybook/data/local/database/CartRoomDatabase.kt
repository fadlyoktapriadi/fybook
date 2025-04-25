package com.fyooo.fybook.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fyooo.fybook.data.local.entity.CartBookEntity

@Database(entities = [CartBookEntity::class], version = 1, exportSchema = false)
abstract class CartRoomDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}