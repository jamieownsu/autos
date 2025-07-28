package com.chalupin.carfax.data.repository

import com.chalupin.carfax.data.api.ListingService
import com.chalupin.carfax.data.db.dao.ListingDao
import com.chalupin.carfax.data.mapper.toDomainList
import com.chalupin.carfax.data.mapper.toDomainListFromEntity
import com.chalupin.carfax.data.mapper.toEntity
import com.chalupin.carfax.data.util.AppError
import com.chalupin.carfax.domain.model.Listing
import com.chalupin.carfax.domain.repository.ListingRepository
import com.chalupin.carfax.presentation.listingdetails.util.ListingDetailsState
import com.chalupin.carfax.presentation.listinglist.util.ListingsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val listingService: ListingService,
    private val listingDao: ListingDao
) : ListingRepository {
    override suspend fun getListings(): Flow<ListingsState<List<Listing>>> = flow {
        try {
            emit(ListingsState.Loading())
            val carFaxDto = listingService.getListings()
            val listingDtos = carFaxDto.listings
            emit(
                ListingsState.Success(
                    data = listingDtos.toDomainList()
                )
            )
            listingDao.insertListings(listingDtos.map { it.toEntity() })
        } catch (e: IOException) {
            emit(
                ListingsState.Offline(
                    data = listingDao.getAllListings().map { it.toDomainListFromEntity() }.first()
                )
            )
        } catch (e: HttpException) {
            val errorMessage = e.response()?.errorBody()?.string() ?: "Unknown API error"
            emit(
                ListingsState.Error(
                    message = AppError.ApiError(e.code(), errorMessage).message!!,
                )
            )
        } catch (e: Exception) {
            emit(
                ListingsState.Error(
                    message = AppError.UnknownError(
                        e.localizedMessage ?: "An unexpected error occurred"
                    ).message!!,
                )
            )
        }
    }

    override suspend fun getListingByVin(vin: String): Flow<ListingDetailsState<Listing?>> = flow {
        try {
            emit(ListingDetailsState.Loading())
            val listings = listingDao.getAllListings().map { it.toDomainListFromEntity() }.first()
            val listing = listings.find { it.vin == vin }
            emit(ListingDetailsState.Success(data = listing))
        } catch (e: Exception) {
            emit(
                ListingDetailsState.Error(
                    message = AppError.UnknownError(
                        e.localizedMessage ?: "An unexpected error occurred"
                    ).message!!
                )
            )
        }
    }
}