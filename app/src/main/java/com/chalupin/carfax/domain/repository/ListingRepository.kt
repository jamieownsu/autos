package com.chalupin.carfax.domain.repository

import com.chalupin.carfax.domain.model.Listing
import com.chalupin.carfax.presentation.listingdetails.util.ListingDetailsState
import com.chalupin.carfax.presentation.listinglist.util.ListingsState
import kotlinx.coroutines.flow.Flow

interface ListingRepository {
    suspend fun getListings(): Flow<ListingsState<List<Listing>>>

    suspend fun getListingByVin(vin: String): Flow<ListingDetailsState<Listing?>>
}