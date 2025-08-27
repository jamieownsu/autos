package com.chalupin.autos.data.api.model

import com.google.gson.annotations.SerializedName

data class CarFaxModel(
    @SerializedName("listings")
    val listings: List<ListingModel>,
)