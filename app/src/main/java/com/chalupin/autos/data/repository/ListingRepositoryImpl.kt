package com.chalupin.autos.data.repository

import com.chalupin.autos.data.api.ListingService
import com.chalupin.autos.data.db.dao.ListingDao
import com.chalupin.autos.data.mapper.toDomain
import com.chalupin.autos.data.mapper.toDomainFromEntity
import com.chalupin.autos.data.mapper.toEntity
import com.chalupin.autos.data.util.OfflineException
import com.chalupin.autos.domain.entity.Listing
import com.chalupin.autos.domain.repository.ListingRepository
import com.chalupin.autos.domain.util.ListingDetailsResponse
import java.io.IOException
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val listingService: ListingService,
    private val listingDao: ListingDao
) : ListingRepository {
    override suspend fun getListings(): List<Listing> {
        try {
            val carFaxDto = listingService.getListings()
            val listingDTOs = carFaxDto.listings
            val entityListings = listingDTOs.map { it.toEntity() }
            listingDao.deleteAllListings()
            listingDao.insertListings(entityListings)
            val domainListings = listingDTOs.map { it.toDomain() }
            return domainListings
        } catch (_: IOException) {
            val listings = listingDao.getAllListings().map { it.toDomainFromEntity() }
            throw OfflineException(listings)
        } catch (e: Exception) {
            throw e
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