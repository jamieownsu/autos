package com.chalupin.autos.data.api.dto

import com.google.gson.annotations.SerializedName

data class ImagesDto(
    @SerializedName("large")
    val large: List<String>
)
