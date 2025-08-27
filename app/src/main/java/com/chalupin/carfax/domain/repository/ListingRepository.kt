package com.chalupin.carfax.domain.repository

import com.chalupin.carfax.domain.entity.Listing
import com.chalupin.carfax.domain.util.ListingDetailsResponse

interface ListingRepository {
    suspend fun getListings(): List<Listing>

    suspend fun getListingByVin(vin: String): ListingDetailsResponse<Listing>
}