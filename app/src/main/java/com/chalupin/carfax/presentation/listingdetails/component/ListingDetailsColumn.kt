package com.chalupin.carfax.presentation.listingdetails.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.chalupin.carfax.R
import com.chalupin.carfax.core.util.formatMileage
import com.chalupin.carfax.core.util.formatPrice
import com.chalupin.carfax.domain.entity.Dealer
import com.chalupin.carfax.domain.entity.Images
import com.chalupin.carfax.domain.entity.Listing
import com.chalupin.carfax.presentation.shared.CallDealerButton

@Composable
fun ListingDetailsColumn(listing: Listing) {
    val imageCount = listing.images?.large?.size ?: 1
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { imageCount })
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
        ) { page ->
            AsyncImage(
                model = listing.getImages()?.get(page),
                contentDescription = "Image of ${listing.make} ${listing.model}",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(300.dp),
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                error = painterResource(R.drawable.ic_launcher_foreground),
            )
        }
        Column(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(
                    id = R.string.year_make_model,
                    listing.year,
                    listing.make,
                    listing.model
                ),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = stringResource(
                    id = R.string.price_mileage,
                    formatPrice(listing.currentPrice),
                    formatMileage(listing.mileage),
                ),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )
        VehicleDetailsRow(listing)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 32.dp),
            thickness = 2.dp,
            color = Color.LightGray
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CallDealerButton(listing.dealer.phone, isDetailsScreen = true)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListingDetailsColumn() {
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
        ListingDetailsColumn(
            listing
        )
    }
}