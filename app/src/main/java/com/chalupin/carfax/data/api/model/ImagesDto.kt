package com.chalupin.carfax.data.api.model

import com.google.gson.annotations.SerializedName

data class ImagesDto(
    @SerializedName("large")
    val large: List<String>
)
