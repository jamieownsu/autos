package com.chalupin.autos.data.mapper

import com.chalupin.autos.data.api.dto.DealerDto
import com.chalupin.autos.data.api.dto.ImagesDto
import com.chalupin.autos.data.api.dto.ListingDto
import com.chalupin.autos.data.db.model.DealerModel
import com.chalupin.autos.data.db.model.ListingModel
import com.chalupin.autos.domain.entity.Dealer
import com.chalupin.autos.domain.entity.Images
import com.chalupin.autos.domain.entity.Listing

fun DealerDto.toDomain(): Dealer {
    return Dealer(
        city = this.city, state = this.state, phone = this.phone
    )
}

fun ImagesDto.toDomain(): Images {
    return Images(large = this.large)
}

fun ListingDto.toDomain(): Listing {
    return Listing(
        dealer = this.dealer.toDomain(),
        vin = this.vin,
        year = this.year,
        make = this.make,
        model = this.model,
        mileage = this.mileage,
        currentPrice = this.currentPrice,
        imageCount = this.imageCount,
        images = this.images?.toDomain(),
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

fun DealerModel.toDomain(): Dealer {
    return Dealer(
        city = this.city, state = this.state, phone = this.phone
    )
}

fun ListingModel.toDomainFromEntity(): Listing {
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