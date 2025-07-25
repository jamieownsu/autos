package com.chalupin.carfax.presentation.listinglist.util

sealed class ListingsState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ListingsState<T>(data)
    class Loading<T>(data: T? = null) : ListingsState<T>(data)
    class Error<T>(data: T? = null, message: String) : ListingsState<T>(data, message)
    class Offline<T>(data: T? = null) : ListingsState<T>(data)
}
