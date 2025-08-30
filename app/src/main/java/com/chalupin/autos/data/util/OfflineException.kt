package com.chalupin.autos.data.util

import com.chalupin.autos.domain.entity.ListingEntity

class OfflineException(val listingEntities: List<ListingEntity>) : Exception()