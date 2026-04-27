package com.example.togalugombe.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Puppet::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun puppetDao(): PuppetDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "togalu_db"
                ).fallbackToDestructiveMigration()
                .allowMainThreadQueries().build()
            }
            return INSTANCE!!
        }
    }
}