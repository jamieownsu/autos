package com.chalupin.autos.presentation.listinglist.util

import com.chalupin.autos.domain.entity.Listing

sealed class ListingsState() {
    object Loading : ListingsState()
    data class Success(val listings: List<Listing>, val isOffline: Boolean = false) :
        ListingsState()

    data class Error(val exception: Exception) : ListingsState()
}
