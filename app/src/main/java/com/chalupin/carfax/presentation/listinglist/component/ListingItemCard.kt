package com.chalupin.carfax.presentation.listinglist.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.chalupin.carfax.R
import com.chalupin.carfax.core.util.formatMileage
import com.chalupin.carfax.core.util.formatPrice
import com.chalupin.carfax.domain.model.Dealer
import com.chalupin.carfax.domain.model.Images
import com.chalupin.carfax.domain.model.Listing
import com.chalupin.carfax.presentation.shared.CallDealerButton

@Composable
fun ListingItemCard(listing: Listing, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onItemClick(listing.vin) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column {
            AsyncImage(
                model = listing.getCoverImage(),
                contentDescription = "Image of ${listing.make} ${listing.model}",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                error = painterResource(R.drawable.ic_launcher_foreground),
            )
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    stringResource(
                        id = R.string.year_make_model,
                        listing.year,
                        listing.make,
                        listing.model
                    ),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    stringResource(
                        id = R.string.price_mileage,
                        formatPrice(listing.currentPrice),
                        formatMileage(listing.mileage),
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    stringResource(
                        id = R.string.city_state, listing.dealer.city, listing.dealer.state
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 2.dp,
                    color = Color.LightGray
                )
                CallDealerButton(phoneNumber = listing.dealer.phone)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListingItemCard() {
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
        ListingItemCard(
            listing,
            onItemClick = {}
        )
    }
}
