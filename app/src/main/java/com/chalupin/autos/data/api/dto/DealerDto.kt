package com.chalupin.autos.data.api.dto

import com.google.gson.annotations.SerializedName

data class DealerDto(
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("phone")
    val phone: String
)
