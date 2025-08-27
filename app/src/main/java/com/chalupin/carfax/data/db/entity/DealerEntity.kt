package com.chalupin.carfax.data.db.entity

import androidx.room.ColumnInfo

data class DealerEntity(
    @ColumnInfo(name = "dealer_city")
    val city: String,
    @ColumnInfo(name = "dealer_state")
    val state: String,
    @ColumnInfo(name = "dealer_phone")
    val phone: String
)
