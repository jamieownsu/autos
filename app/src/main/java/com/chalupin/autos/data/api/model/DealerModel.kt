package com.chalupin.autos.data.api.model

import com.google.gson.annotations.SerializedName

data class DealerModel(
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("phone")
    val phone: String
)
