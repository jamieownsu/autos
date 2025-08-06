package com.chalupin.carfax.domain.util

sealed class ListingsResponse<out T> {
    data class Success<out T>(val data: T) : ListingsResponse<T>()
    data class Offline<out T>(val data: T) : ListingsResponse<T>()
    data class Error(val exception: Exception) : ListingsResponse<Nothing>()
}