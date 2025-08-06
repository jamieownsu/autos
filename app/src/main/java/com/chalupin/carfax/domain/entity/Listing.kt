package com.chalupin.carfax.domain.entity

data class Listing(
    val dealer: Dealer,
    val vin: String,
    val year: Int,
    val make: String,
    val model: String,
    val mileage: Int,
    val currentPrice: Float,
    val imageCount: Int,
    val images: Images?,
    val exteriorColor: String,
    val interiorColor: String,
    val engine: String,
    val driveType: String,
    val transmission: String,
    val fuel: String,
    val bodyType: String,
) {
    fun getCoverImage(): String? {
        if (images?.large?.isNotEmpty() == true) {
            return images.large.first()
        }
        return null
    }

    fun getImages(): List<String>? {
        if (images?.large?.isNotEmpty() == true) {
            return images.large
        }
        return null
    }
}

