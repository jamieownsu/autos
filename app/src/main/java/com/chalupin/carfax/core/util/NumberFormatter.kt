package com.chalupin.carfax.core.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun formatPrice(price: Float): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    numberFormat.minimumFractionDigits = 0
    numberFormat.maximumFractionDigits = 0
    return "$ ${numberFormat.format(price)}"
}

fun formatMileage(mileage: Int): String {
    if (mileage < 1000) {
        return mileage.toString()
    }
    val formatOneDecimal = DecimalFormat("0.0")
    return when {
        mileage >= 1_000_000_000 -> {
            val billions = mileage.toDouble() / 1_000_000_000.0
            "${formatOneDecimal.format(billions)}B"
        }

        mileage >= 1_000_000 -> {
            val millions = mileage.toDouble() / 1_000_000.0
            "${formatOneDecimal.format(millions)}M"
        }

        else -> {
            val thousands = mileage.toDouble() / 1000.0
            "${formatOneDecimal.format(thousands)}k"
        }
    }
}