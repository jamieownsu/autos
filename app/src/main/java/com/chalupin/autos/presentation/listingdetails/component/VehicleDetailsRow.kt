package com.chalupin.autos.presentation.listingdetails.component

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
import com.chalupin.autos.R
import com.chalupin.autos.domain.entity.DealerEntity
import com.chalupin.autos.domain.entity.ImagesEntity
import com.chalupin.autos.domain.entity.ListingEntity

@Composable
fun VehicleDetailsRow(listingEntity: ListingEntity) {
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
                        listingEntity.dealerEntity.city,
                        listingEntity.dealerEntity.state
                    ),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    listingEntity.exteriorColor,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    listingEntity.interiorColor,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    listingEntity.driveType,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    listingEntity.transmission,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    listingEntity.bodyType,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    listingEntity.engine,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    listingEntity.fuel,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVehicleDetailsRow() {
    val listingEntity = ListingEntity(
        dealerEntity = DealerEntity(city = "City", state = "State", phone = "555-555-5555"),
        vin = "vin",
        year = 2000,
        make = "",
        model = "",
        mileage = 10000,
        currentPrice = 9999F,
        imageCount = 3,
        imagesEntity = ImagesEntity(large = emptyList()),
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
            listingEntity
        )
    }
}
