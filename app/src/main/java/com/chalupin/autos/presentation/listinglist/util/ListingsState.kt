package com.chalupin.autos.presentation.listinglist.util

import com.chalupin.autos.domain.entity.ListingEntity

sealed class ListingsState() {
    object Loading : ListingsState()
    data class Success(val listingEntities: List<ListingEntity>, val isOffline: Boolean = false) :
        ListingsState()

    data class Error(val exception: Exception) : ListingsState()
}
