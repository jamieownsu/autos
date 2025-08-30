package com.chalupin.autos.domain.repository

import com.chalupin.autos.domain.entity.ListingEntity
import com.chalupin.autos.domain.util.ListingDetailsResponse

interface ListingRepository {
    suspend fun getListings(): List<ListingEntity>

    suspend fun getListingByVin(vin: String): ListingDetailsResponse<ListingEntity>
}