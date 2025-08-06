package com.chalupin.carfax.data.repository

import com.chalupin.carfax.data.api.ListingService
import com.chalupin.carfax.data.db.dao.ListingDao
import com.chalupin.carfax.data.mapper.toDomain
import com.chalupin.carfax.data.mapper.toDomainFromEntity
import com.chalupin.carfax.data.mapper.toEntity
import com.chalupin.carfax.domain.model.Listing
import com.chalupin.carfax.domain.repository.ListingRepository
import com.chalupin.carfax.domain.util.ListingDetailsResponse
import com.chalupin.carfax.domain.util.ListingsResponse
import java.io.IOException
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val listingService: ListingService,
    private val listingDao: ListingDao
) : ListingRepository {
    override suspend fun getListings(): ListingsResponse<List<Listing>> {
        try {
            val carFaxDto = listingService.getListings()
            val listingDTOs = carFaxDto.listings
            val entityListings = listingDTOs.map { it.toEntity() }
            listingDao.deleteAllListings()
            listingDao.insertListings(entityListings)
            val domainListings = listingDTOs.map { it.toDomain() }
            return ListingsResponse.Success(domainListings)
        } catch (_: IOException) {
            val listings = listingDao.getAllListings().map { it.toDomainFromEntity() }
            return ListingsResponse.Offline(listings)
        } catch (e: Exception) {
            return ListingsResponse.Error(e)
        }
    }

    override suspend fun getListingByVin(vin: String): ListingDetailsResponse<Listing> {
        try {
            val listings = listingDao.getAllListings().map { it.toDomainFromEntity() }
            val listing = listings.find { it.vin == vin }
            return listing?.let {
                ListingDetailsResponse.Success(it)
            } ?: run {
                ListingDetailsResponse.Error(Exception("Listing not found"))
            }
        } catch (e: Exception) {
            return ListingDetailsResponse.Error(e)
        }
    }
}