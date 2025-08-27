package com.chalupin.carfax.domain.usecase

import com.chalupin.carfax.data.util.OfflineException
import com.chalupin.carfax.domain.entity.Listing
import com.chalupin.carfax.domain.repository.ListingRepository
import com.chalupin.carfax.domain.util.ListingsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetListingsUseCase @Inject constructor(
    private val listingRepository: ListingRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): ListingsResponse<List<Listing>> {
        return withContext(ioDispatcher) {
            try {
                ListingsResponse.Success(listingRepository.getListings())
            } catch (e: OfflineException) {
                ListingsResponse.Offline(e.listings)
            } catch (e: Exception) {
                ListingsResponse.Error(e)
            }
        }
    }
}