package com.chalupin.carfax.data.util

import com.chalupin.carfax.domain.entity.Listing

class OfflineException(val listings: List<Listing>) : Exception()