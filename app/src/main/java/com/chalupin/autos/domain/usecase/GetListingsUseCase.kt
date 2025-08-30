package com.chalupin.autos.domain.usecase

import com.chalupin.autos.data.util.OfflineException
import com.chalupin.autos.domain.entity.ListingEntity
import com.chalupin.autos.domain.repository.ListingRepository
import com.chalupin.autos.domain.util.ListingsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetListingsUseCase @Inject constructor(
    private val listingRepository: ListingRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): ListingsResponse<List<ListingEntity>> {
        return withContext(ioDispatcher) {
            try {
                ListingsResponse.Success(listingRepository.getListings())
            } catch (e: OfflineException) {
                ListingsResponse.Offline(e.listingEntities)
            } catch (e: Exception) {
                ListingsResponse.Error(e)
            }
        }
    }
}