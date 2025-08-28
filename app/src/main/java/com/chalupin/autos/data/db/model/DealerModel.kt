package com.chalupin.autos.data.db.model

import androidx.room.ColumnInfo

data class DealerModel(
    @ColumnInfo(name = "dealer_city")
    val city: String,
    @ColumnInfo(name = "dealer_state")
    val state: String,
    @ColumnInfo(name = "dealer_phone")
    val phone: String
)
