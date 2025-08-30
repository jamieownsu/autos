package com.chalupin.autos.data.mapper

import com.chalupin.autos.data.api.dto.DealerDto
import com.chalupin.autos.data.api.dto.ImagesDto
import com.chalupin.autos.data.api.dto.ListingDto
import com.chalupin.autos.data.db.model.DealerModel
import com.chalupin.autos.data.db.model.ListingModel
import com.chalupin.autos.domain.entity.DealerEntity
import com.chalupin.autos.domain.entity.ImagesEntity
import com.chalupin.autos.domain.entity.ListingEntity

fun DealerDto.toDomain(): DealerEntity {
    return DealerEntity(
        city = this.city, state = this.state, phone = this.phone
    )
}

fun ImagesDto.toDomain(): ImagesEntity {
    return ImagesEntity(large = this.large)
}

fun ListingDto.toDomain(): ListingEntity {
    return ListingEntity(
        dealerEntity = this.dealer.toDomain(),
        vin = this.vin,
        year = this.year,
        make = this.make,
        model = this.model,
        mileage = this.mileage,
        currentPrice = this.currentPrice,
        imageCount = this.imageCount,
        imagesEntity = this.images?.toDomain(),
        exteriorColor = this.exteriorColor,
        interiorColor = this.interiorColor,
        engine = this.engine,
        driveType = this.driveType,
        transmission = this.transmission,
        fuel = this.fuel,
        bodyType = this.bodyType
    )
}

fun DealerDto.toEntity(): DealerModel {
    return DealerModel(
        city = this.city, state = this.state, phone = this.phone
    )
}

fun ListingDto.toEntity(): ListingModel {
    return ListingModel(
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

fun DealerModel.toDomain(): DealerEntity {
    return DealerEntity(
        city = this.city, state = this.state, phone = this.phone
    )
}

fun ListingModel.toDomainFromEntity(): ListingEntity {
    return ListingEntity(
        dealerEntity = this.dealer.toDomain(),
        imagesEntity = ImagesEntity(this.images),
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