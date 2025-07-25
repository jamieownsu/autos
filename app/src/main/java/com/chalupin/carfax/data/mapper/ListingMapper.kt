package com.chalupin.carfax.data.mapper

import com.chalupin.carfax.data.api.model.DealerDto
import com.chalupin.carfax.data.api.model.ListingDto
import com.chalupin.carfax.data.db.entity.DealerEntity
import com.chalupin.carfax.data.db.entity.ListingEntity
import com.chalupin.carfax.domain.model.Dealer
import com.chalupin.carfax.domain.model.Images
import com.chalupin.carfax.domain.model.Listing

fun DealerDto.toEntity(): DealerEntity {
    return DealerEntity(
        city = this.city,
        state = this.state,
        phone = this.phone
    )
}

fun ListingDto.toEntity(): ListingEntity {
    return ListingEntity(
        dealer = this.dealer.toEntity(),
        images = this.images?.large,
        year = this.year,
        make = this.make,
        model = this.model,
        mileage = this.mileage,
        currentPrice = this.currentPrice,
        imageCount = this.imageCount,
        exteriorColor = this.exteriorColor,
        interiorColor = this.interiorColor,
        engine = this.engine,
        driveType = this.driveType,
        transmission = this.transmission,
        fuel = this.fuel,
        bodyType = this.bodyType,
        vin = this.vin,
    )
}

fun DealerEntity.toDomain(): Dealer {
    return Dealer(
        city = this.city,
        state = this.state,
        phone = this.phone
    )
}

fun ListingEntity.toDomainFromEntity(): Listing {
    return Listing(
        dealer = this.dealer.toDomain(),
        images = Images(this.images),
        year = this.year,
        make = this.make,
        model = this.model,
        mileage = this.mileage,
        currentPrice = this.currentPrice,
        imageCount = this.imageCount,
        exteriorColor = this.exteriorColor,
        interiorColor = this.interiorColor,
        engine = this.engine,
        driveType = this.driveType,
        transmission = this.transmission,
        fuel = this.fuel,
        bodyType = this.bodyType,
        vin = this.vin,
    )
}

fun List<ListingEntity>.toDomainListFromEntity(): List<Listing> {
    return map { it.toDomainFromEntity() }
}