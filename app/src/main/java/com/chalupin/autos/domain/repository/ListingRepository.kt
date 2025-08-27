package com.chalupin.autos.domain.repository

import com.chalupin.autos.domain.entity.Listing
import com.chalupin.autos.domain.util.ListingDetailsResponse

interface ListingRepository {
    suspend fun getListings(): List<Listing>

    suspend fun getListingByVin(vin: String): ListingDetailsResponse<Listing>
}