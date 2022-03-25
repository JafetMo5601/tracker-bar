package com.finalproject.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.finalproject.model.Reservation

@Database(entities = [Reservation::class], version = 1, exportSchema = false)
abstract class TrackerBarDB: RoomDatabase() {
    abstract fun reservationDAO(): ReservationDAO

    companion object {
        @Volatile
        private var INSTANCE: TrackerBarDB? = null

        fun getDatabase(context: android.content.Context): TrackerBarDB {
            var tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TrackerBarDB::class.java,
                    "tracker_bar_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}