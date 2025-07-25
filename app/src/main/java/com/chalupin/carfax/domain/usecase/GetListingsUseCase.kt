package com.chalupin.carfax.domain.usecase

import com.chalupin.carfax.domain.model.Listing
import com.chalupin.carfax.domain.repository.ListingRepository
import com.chalupin.carfax.presentation.listinglist.util.ListingsState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListingsUseCase @Inject constructor(
    private val listingRepository: ListingRepository
) {
    suspend operator fun invoke(): Flow<ListingsState<List<Listing>>> {
        return listingRepository.getListings()
    }
}