package com.chalupin.carfax.data.util

sealed class AppError(message: String) : Exception(message) {
    class NetworkError(message: String = "Network error. Please check your internet connection.") :
        AppError(message)

    class ApiError(val code: Int, message: String = "An API error occurred.") : AppError(message)
    class UnknownError(message: String = "An unexpected error occurred.") : AppError(message)
}