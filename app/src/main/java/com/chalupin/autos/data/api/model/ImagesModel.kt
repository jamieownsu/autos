package com.chalupin.autos.data.api.model

import com.google.gson.annotations.SerializedName

data class ImagesModel(
    @SerializedName("large")
    val large: List<String>
)
