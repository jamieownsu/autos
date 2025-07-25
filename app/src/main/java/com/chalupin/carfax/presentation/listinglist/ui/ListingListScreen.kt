package com.chalupin.carfax.presentation.listinglist.ui

import NavRoutes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chalupin.carfax.presentation.listinglist.component.ListingItemCard
import com.chalupin.carfax.presentation.listinglist.component.ListingListAppBar
import com.chalupin.carfax.presentation.listinglist.viewmodel.ListingListViewModel

@Composable
fun ListingListScreen(
    navController: NavController,
    viewModel: ListingListViewModel = hiltViewModel()
) {
    val listings by viewModel.listings.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Scaffold(topBar = { ListingListAppBar() }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 16.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else if (error != null) {
                Text("Error: $error", color = MaterialTheme.colorScheme.error)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(listings) { listing ->
                        ListingItemCard(
                            listing = listing,
                            onItemClick = { clickedListingVin ->
                                navController.navigate("${NavRoutes.LISTING_DETAIL}/$clickedListingVin")
                            })
                    }
                }
            }
        }
    }
}
