package com.chalupin.carfax.domain.usecase

import com.chalupin.carfax.domain.entity.Listing
import com.chalupin.carfax.domain.repository.ListingRepository
import com.chalupin.carfax.domain.util.ListingDetailsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetListingDetailsUseCase @Inject constructor(
    private val listingRepository: ListingRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(vin: String): ListingDetailsResponse<Listing> {
        return withContext(ioDispatcher) { listingRepository.getListingByVin(vin) }
    }
}