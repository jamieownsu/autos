package com.chalupin.carfax.presentation.listinglist.util

sealed class ListingsEvent {
    data object LoadListingsEvent : ListingsEvent()
}