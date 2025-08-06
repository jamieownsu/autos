package com.chalupin.carfax.presentation.listingdetails.util

import com.chalupin.carfax.domain.model.Listing

sealed class ListingDetailsState {
    object Loading : ListingDetailsState()

    class Success(val listingDetails: Listing) : ListingDetailsState()

    class Error(val exception: Exception) : ListingDetailsState()
}
