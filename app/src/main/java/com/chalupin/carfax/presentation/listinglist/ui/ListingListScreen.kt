package com.chalupin.carfax.presentation.listinglist.ui

import NavRoutes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chalupin.carfax.presentation.listinglist.component.ListingItemCard
import com.chalupin.carfax.presentation.listinglist.component.ListingListAppBar
import com.chalupin.carfax.presentation.listinglist.util.ListingsEvent
import com.chalupin.carfax.presentation.listinglist.viewmodel.ListingListViewModel
import com.chalupin.carfax.presentation.shared.ErrorScreen
import com.chalupin.carfax.presentation.shared.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingListScreen(
    navController: NavController,
    viewModel: ListingListViewModel = hiltViewModel()
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val pullToRefreshState = rememberPullToRefreshState()

    val listings by viewModel.listings.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val isOffline by viewModel.isOffline.collectAsState()

    Scaffold(
        topBar = {
            ListingListAppBar(isOffline, snackBarHostState)
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = isLoading,
            onRefresh = { viewModel.handleEvent(ListingsEvent.LoadListingsEvent) },
            state = pullToRefreshState,
            content = {
                if (isLoading) {
                    LoadingScreen(innerPadding)
                } else if (error != null) {
                    ErrorScreen(innerPadding)
                } else {
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
            })
    }
}
