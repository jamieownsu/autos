package com.chalupin.carfax.domain.repository

import com.chalupin.carfax.domain.entity.Listing
import com.chalupin.carfax.domain.util.ListingDetailsResponse
import com.chalupin.carfax.domain.util.ListingsResponse

interface ListingRepository {
    suspend fun getListings(): ListingsResponse<List<Listing>>

    suspend fun getListingByVin(vin: String): ListingDetailsResponse<Listing>
}