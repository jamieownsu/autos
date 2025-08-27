package com.chalupin.autos.data.mapper

import com.chalupin.autos.data.api.model.DealerModel
import com.chalupin.autos.data.api.model.ImagesModel
import com.chalupin.autos.data.api.model.ListingModel
import com.chalupin.autos.data.db.entity.DealerEntity
import com.chalupin.autos.data.db.entity.ListingEntity
import com.chalupin.autos.domain.entity.Dealer
import com.chalupin.autos.domain.entity.Images
import com.chalupin.autos.domain.entity.Listing

fun DealerModel.toDomain(): Dealer {
    return Dealer(
        city = this.city, state = this.state, phone = this.phone
    )
}

fun ImagesModel.toDomain(): Images {
    return Images(large = this.large)
}

fun ListingModel.toDomain(): Listing {
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

fun DealerModel.toEntity(): DealerEntity {
    return DealerEntity(
        city = this.city, state = this.state, phone = this.phone
    )
}

fun ListingModel.toEntity(): ListingEntity {
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
        city = this.city, state = this.state, phone = this.phone
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