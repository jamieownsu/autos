package com.chalupin.carfax.data.api.model

import com.google.gson.annotations.SerializedName

data class ListingModel(
    @SerializedName("dealer")
    val dealer: DealerModel,
    @SerializedName("vin")
    val vin: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("make")
    val make: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("mileage")
    val mileage: Int,
    @SerializedName("currentPrice")
    val currentPrice: Float,
    @SerializedName("imageCount")
    val imageCount: Int,
    @SerializedName("images")
    val images: ImagesModel?,
    @SerializedName("exteriorColor")
    val exteriorColor: String,
    @SerializedName("interiorColor")
    val interiorColor: String,
    @SerializedName("engine")
    val engine: String,
    @SerializedName("drivetype")
    val driveType: String,
    @SerializedName("transmission")
    val transmission: String,
    @SerializedName("fuel")
    val fuel: String,
    @SerializedName("bodytype")
    val bodyType: String,
)