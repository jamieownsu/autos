package com.chalupin.autos.data.util

import com.chalupin.autos.domain.entity.Listing

class OfflineException(val listings: List<Listing>) : Exception()