package com.chalupin.autos.data.api.dto

import com.google.gson.annotations.SerializedName

data class CarFaxDto(
    @SerializedName("listings")
    val listings: List<ListingDto>,
)