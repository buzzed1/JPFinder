package com.buzzed.jpfinder.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [JP::class], version = 3, exportSchema = true, autoMigrations = [
    AutoMigration (from = 2, to = 3)
] )
abstract class JPFinderDatabase : RoomDatabase() {
    abstract fun JPDao(): JPDao

    companion object {
        @Volatile
        private var INSTANCE: JPFinderDatabase? = null

        fun getDatabase(context: Context): JPFinderDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, JPFinderDatabase::class.java, "jp_database")
                    .createFromAsset("database/jp_database.db")
                    .build()
                    .also { INSTANCE = it }
            }
        }

    }
}