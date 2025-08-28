package com.chalupin.autos.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chalupin.autos.data.db.model.ListingModel

@Dao
interface ListingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListings(listings: List<ListingModel>)

    @Query("SELECT * FROM listings")
    suspend fun getAllListings(): List<ListingModel>

    @Query("DELETE FROM listings")
    suspend fun deleteAllListings()
}