package com.chalupin.carfax.presentation.listingdetails.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.chalupin.carfax.R
import com.chalupin.carfax.core.util.formatMileage
import com.chalupin.carfax.core.util.formatPrice
import com.chalupin.carfax.presentation.listingdetails.component.CallDealerButton
import com.chalupin.carfax.presentation.listingdetails.viewmodel.ListingDetailViewModel

@Composable
fun ListingDetailScreen(
    viewModel: ListingDetailViewModel = hiltViewModel(),
    vin: String,
) {
    viewModel.fetchListingDetails(vin)

    val listingDetails by viewModel.listingDetails.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) { CircularProgressIndicator() }
    } else if (error != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) { Text(text = "Error: $error", color = MaterialTheme.colorScheme.error) }
    } else {
        listingDetails?.let {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                AsyncImage(
                    model = it.getCoverImage(),
                    contentDescription = "Image of ${it.make} ${it.model}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(300.dp),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                    error = painterResource(R.drawable.ic_launcher_foreground),
                )
                Column(
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.year_make_model,
                            it.year,
                            it.make,
                            it.model
                        ),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = stringResource(
                            id = R.string.price_mileage,
                            formatPrice(it.currentPrice),
                            formatMileage(it.mileage),
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
                                    it.dealer.city,
                                    it.dealer.state
                                ),
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                it.exteriorColor,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                it.interiorColor,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                it.driveType,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                it.transmission,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                it.bodyType,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                it.engine,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                it.fuel,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 32.dp),
                    thickness = 2.dp,
                    color = Color.LightGray
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CallDealerButton(it.dealer.phone, isDetailsScreen = true)
                }
            }
        } ?: run {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) { Text(text = "No details found for VIN: $vin") }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ListingDetailsAppBar(listingDetails: Listing?, navController: NavHostController) {
//    TopAppBar(
//        title = {},
////      backgroundColor = MaterialTheme.colors.primaryVariant,
////      contentColor = MaterialTheme.colors.onPrimary,
//        navigationIcon = {
//            IconButton(onClick = { navController.popBackStack() }) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                    contentDescription = "Back"
//                )
//            }
//        }
//    )
//}