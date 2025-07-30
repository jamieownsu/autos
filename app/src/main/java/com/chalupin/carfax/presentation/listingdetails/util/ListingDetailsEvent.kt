package com.chalupin.carfax.presentation.listingdetails.util

sealed class ListingDetailsEvent {
    data class LoadListingDetailsEvent(val vin: String) : ListingDetailsEvent()
}