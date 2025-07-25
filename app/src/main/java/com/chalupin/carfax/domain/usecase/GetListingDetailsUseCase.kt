package com.chalupin.carfax.domain.usecase

import com.chalupin.carfax.domain.model.Listing
import com.chalupin.carfax.domain.repository.ListingRepository
import com.chalupin.carfax.presentation.listingdetails.util.ListingDetailsState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListingDetailsUseCase @Inject constructor(
    private val listingRepository: ListingRepository
) {
    suspend operator fun invoke(vin: String): Flow<ListingDetailsState<Listing?>> {
        return listingRepository.getListingByVin(vin)
    }
}