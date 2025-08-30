package com.chalupin.autos.presentation.listingdetails.component

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
import com.chalupin.autos.R
import com.chalupin.autos.core.util.formatMileage
import com.chalupin.autos.core.util.formatPrice
import com.chalupin.autos.domain.entity.DealerEntity
import com.chalupin.autos.domain.entity.ImagesEntity
import com.chalupin.autos.domain.entity.ListingEntity
import com.chalupin.autos.presentation.shared.CallDealerButton

@Composable
fun ListingDetailsColumn(listingEntity: ListingEntity) {
    val imageCount = listingEntity.imagesEntity?.large?.size ?: 1
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { imageCount })
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
        ) { page ->
            AsyncImage(
                model = listingEntity.getImages()?.get(page),
                contentDescription = "Image of ${listingEntity.make} ${listingEntity.model}",
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
                    listingEntity.year,
                    listingEntity.make,
                    listingEntity.model
                ),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = stringResource(
                    id = R.string.price_mileage,
                    formatPrice(listingEntity.currentPrice),
                    formatMileage(listingEntity.mileage),
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
        VehicleDetailsRow(listingEntity)
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 32.dp),
            thickness = 2.dp,
            color = Color.LightGray
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CallDealerButton(listingEntity.dealerEntity.phone, isDetailsScreen = true)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListingDetailsColumn() {
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
        ListingDetailsColumn(
            listingEntity
        )
    }
}