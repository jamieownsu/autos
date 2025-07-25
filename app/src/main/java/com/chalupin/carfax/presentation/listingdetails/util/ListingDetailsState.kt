package com.chalupin.carfax.presentation.listingdetails.util

sealed class ListingDetailsState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ListingDetailsState<T>(data)
    class Loading<T>(data: T? = null) : ListingDetailsState<T>(data)
    class Error<T>(data: T? = null, message: String) : ListingDetailsState<T>(data, message)
}
