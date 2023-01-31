package com.example.adi.learnshala.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WorkshopEntity::class], version = 1)
abstract class WorkshopDatabase: RoomDatabase() {

    abstract fun workshopDao(): WorkshopDao

    companion object {
        @Volatile
        private var INSTANCE: WorkshopDatabase? = null
        private const val WORKSHOP_DATABASE = "workshop_database"

        fun getInstance(context: Context): WorkshopDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, WorkshopDatabase::class.java, WORKSHOP_DATABASE)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}