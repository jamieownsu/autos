package com.chalupin.carfax.data.api.model

import com.google.gson.annotations.SerializedName

data class CarFaxDto(
    @SerializedName("listings")
    val listings: List<ListingDto>,
)