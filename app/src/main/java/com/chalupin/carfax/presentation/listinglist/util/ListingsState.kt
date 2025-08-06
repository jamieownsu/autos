package com.chalupin.carfax.presentation.listinglist.util

import com.chalupin.carfax.domain.model.Listing

sealed class ListingsState() {
    object Loading : ListingsState()
    data class Success(val listings: List<Listing>, val isOffline: Boolean = false) :
        ListingsState()

    data class Error(val exception: Exception) : ListingsState()
}
