package com.chalupin.autos.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "listings")
data class ListingModel(
    @PrimaryKey
    @ColumnInfo(name = "vin")
    val vin: String,
    @ColumnInfo(name = "year")
    val year: Int,
    @ColumnInfo(name = "make")
    val make: String,
    @ColumnInfo(name = "model")
    val model: String,
    @ColumnInfo(name = "price")
    val currentPrice: Float,
    @Embedded(prefix = "dealer_")
    val dealer: DealerModel,
    @ColumnInfo(name = "images")
    val images: List<String>?,
    @ColumnInfo(name = "mileage")
    val mileage: Int,
    @ColumnInfo(name = "imageCount")
    val imageCount: Int,
    @ColumnInfo(name = "exteriorColor")
    val exteriorColor: String,
    @ColumnInfo(name = "interiorColor")
    val interiorColor: String,
    @ColumnInfo(name = "engine")
    val engine: String,
    @ColumnInfo(name = "driveType")
    val driveType: String,
    @ColumnInfo(name = "transmission")
    val transmission: String,
    @ColumnInfo(name = "fuel")
    val fuel: String,
    @ColumnInfo(name = "bodyType")
    val bodyType: String,
)