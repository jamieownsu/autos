package com.chalupin.autos.presentation.listinglist.util

sealed class ListingsEvent {
    data object LoadListingsEvent : ListingsEvent()
}