package com.chalupin.autos.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chalupin.autos.data.db.entity.ListingEntity

@Dao
interface ListingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListings(listings: List<ListingEntity>)

    @Query("SELECT * FROM listings")
    suspend fun getAllListings(): List<ListingEntity>

    @Query("DELETE FROM listings")
    suspend fun deleteAllListings()
}