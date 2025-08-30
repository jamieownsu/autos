package com.chalupin.autos.presentation.listingdetails.util

import com.chalupin.autos.domain.entity.ListingEntity

sealed class ListingDetailsState {
    object Loading : ListingDetailsState()

    class Success(val listingEntityDetails: ListingEntity) : ListingDetailsState()

    class Error(val exception: Exception) : ListingDetailsState()
}
