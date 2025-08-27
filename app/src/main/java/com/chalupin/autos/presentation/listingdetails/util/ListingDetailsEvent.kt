package com.chalupin.autos.presentation.listingdetails.util

sealed class ListingDetailsEvent {
    data class LoadListingDetailsEvent(val vin: String) : ListingDetailsEvent()
}