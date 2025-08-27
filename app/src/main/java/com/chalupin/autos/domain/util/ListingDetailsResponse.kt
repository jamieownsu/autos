package com.chalupin.autos.domain.util

sealed class ListingDetailsResponse<out T> {
    data class Success<out T>(val data: T) : ListingDetailsResponse<T>()
    data class Error(val exception: Exception) : ListingDetailsResponse<Nothing>()
}