package com.chalupin.carfax.presentation.listingdetails.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chalupin.carfax.R
import com.chalupin.carfax.domain.model.Dealer
import com.chalupin.carfax.domain.model.Images
import com.chalupin.carfax.domain.model.Listing

@Composable
fun VehicleDetailsRow(listing: Listing) {
    Column(
        modifier = Modifier.padding(horizontal = 32.dp),
    ) {
        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = stringResource(
                id = R.string.vehicle_info,
            ),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(0.75f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    stringResource(id = R.string.location),
                    color = Color.Gray,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    stringResource(id = R.string.exterior_color), color = Color.Gray,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    stringResource(id = R.string.interior_color), color = Color.Gray,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    stringResource(id = R.string.drive_type), color = Color.Gray,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    stringResource(id = R.string.transmission), color = Color.Gray,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    stringResource(id = R.string.body_style), color = Color.Gray,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    stringResource(id = R.string.engine), color = Color.Gray,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    stringResource(id = R.string.fuel), color = Color.Gray,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Column {
                Text(
                    stringResource(
                        id = R.string.city_state,
                        listing.dealer.city,
                        listing.dealer.state
                    ),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    listing.exteriorColor,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    listing.interiorColor,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    listing.driveType,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    listing.transmission,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    listing.bodyType,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    listing.engine,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    listing.fuel,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVehicleDetailsRow() {
    val listing = Listing(
        dealer = Dealer(city = "City", state = "State", phone = "555-555-5555"),
        vin = "vin",
        year = 2000,
        make = "",
        model = "",
        mileage = 10000,
        currentPrice = 9999F,
        imageCount = 3,
        images = Images(large = emptyList()),
        exteriorColor = "",
        interiorColor = "",
        engine = "",
        driveType = "",
        transmission = "",
        fuel = "",
        bodyType = ""
    )
    MaterialTheme {
        VehicleDetailsRow(
            listing
        )
    }
}
