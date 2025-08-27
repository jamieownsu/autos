package com.chalupin.autos.presentation.listingdetails.util

import com.chalupin.autos.domain.entity.Listing

sealed class ListingDetailsState {
    object Loading : ListingDetailsState()

    class Success(val listingDetails: Listing) : ListingDetailsState()

    class Error(val exception: Exception) : ListingDetailsState()
}
