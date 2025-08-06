package com.chalupin.carfax.presentation.listingdetails.util

import com.chalupin.carfax.domain.entity.Listing

sealed class ListingDetailsState {
    object Loading : ListingDetailsState()

    class Success(val listingDetails: Listing) : ListingDetailsState()

    class Error(val exception: Exception) : ListingDetailsState()
}
