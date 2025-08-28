package com.chalupin.autos.data.api

import com.chalupin.autos.data.api.dto.CarFaxDto
import retrofit2.http.GET

interface ListingService {
    @GET("assignment.json")
    suspend fun getListings(): CarFaxDto
}