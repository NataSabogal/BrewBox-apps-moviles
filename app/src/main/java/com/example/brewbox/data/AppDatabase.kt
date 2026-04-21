package com.example.brewbox.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 2, exportSchema = false) // Subimos a versión 2
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "brewbox_database"
                )
                .fallbackToDestructiveMigration() // Esto evita que la app se crashee al cambiar el esquema
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
