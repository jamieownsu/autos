package com.chalupin.carfax.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chalupin.carfax.data.db.converter.Converters
import com.chalupin.carfax.data.db.dao.ListingDao
import com.chalupin.carfax.data.db.entity.ListingEntity

@Database(
    entities = [ListingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ListingDatabase : RoomDatabase() {
    abstract fun listingDao(): ListingDao

    companion object {
        @Volatile
        private var INSTANCE: ListingDatabase? = null

        fun getDatabase(context: Context): ListingDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ListingDatabase::class.java,
                        "carfax_listing_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    instance
                }
        }
    }
}