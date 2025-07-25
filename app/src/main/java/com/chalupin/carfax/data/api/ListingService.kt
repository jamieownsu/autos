package com.chalupin.carfax.data.api

import com.chalupin.carfax.data.api.model.CarFaxDto
import retrofit2.http.GET

interface ListingService {
    @GET("assignment.json")
    suspend fun getListings(): CarFaxDto
}