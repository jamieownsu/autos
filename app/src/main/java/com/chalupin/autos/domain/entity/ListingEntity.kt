package com.chalupin.autos.domain.entity

data class ListingEntity(
    val dealerEntity: DealerEntity,
    val vin: String,
    val year: Int,
    val make: String,
    val model: String,
    val mileage: Int,
    val currentPrice: Float,
    val imageCount: Int,
    val imagesEntity: ImagesEntity?,
    val exteriorColor: String,
    val interiorColor: String,
    val engine: String,
    val driveType: String,
    val transmission: String,
    val fuel: String,
    val bodyType: String,
) {
    fun getCoverImage(): String? {
        if (imagesEntity?.large?.isNotEmpty() == true) {
            return imagesEntity.large.first()
        }
        return null
    }

    fun getImages(): List<String>? {
        if (imagesEntity?.large?.isNotEmpty() == true) {
            return imagesEntity.large
        }
        return null
    }
}

