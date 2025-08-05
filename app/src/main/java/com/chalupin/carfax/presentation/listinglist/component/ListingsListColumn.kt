package com.chalupin.carfax.presentation.listinglist.component

import NavRoutes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chalupin.carfax.domain.model.Dealer
import com.chalupin.carfax.domain.model.Images
import com.chalupin.carfax.domain.model.Listing

@Composable
fun ListingsListColumn(
    innerPadding: PaddingValues,
    navController: NavController,
    listings: List<Listing>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(listings) { listing ->
            ListingItemCard(
                listing = listing,
                onItemClick = { vin ->
                    navController.navigate("${NavRoutes.LISTING_DETAIL}/$vin")
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListingsListColumnPreview() {
    val mockNavController = rememberNavController()
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
    val sampleListings = listOf(
        listing
    )
    ListingsListColumn(
        innerPadding = PaddingValues(16.dp),
        navController = mockNavController,
        listings = sampleListings
    )
}