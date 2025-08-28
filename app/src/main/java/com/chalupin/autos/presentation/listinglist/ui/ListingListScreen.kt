package com.chalupin.autos.presentation.listinglist.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.chalupin.autos.presentation.listinglist.component.ListingsListAppBar
import com.chalupin.autos.presentation.listinglist.component.ListingsListColumn
import com.chalupin.autos.presentation.listinglist.util.ListingsEvent
import com.chalupin.autos.presentation.listinglist.util.ListingsState
import com.chalupin.autos.presentation.listinglist.viewmodel.ListingListViewModel
import com.chalupin.autos.presentation.shared.ErrorScreen
import com.chalupin.autos.presentation.shared.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingListScreen(
    navController: NavController,
    viewModel: ListingListViewModel = hiltViewModel()
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val pullToRefreshState = rememberPullToRefreshState()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val isOffline = (uiState as? ListingsState.Success)?.isOffline ?: false

    Scaffold(
        topBar = {
            ListingsListAppBar(isOffline, snackBarHostState)
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = uiState is ListingsState.Loading,
            onRefresh = { viewModel.handleEvent(ListingsEvent.LoadListingsEvent) },
            state = pullToRefreshState,
            content = {
                when (val state = uiState) {
                    is ListingsState.Loading -> LoadingScreen(innerPadding)
                    is ListingsState.Success -> {
                        ListingsListColumn(
                            innerPadding,
                            navController,
                            state.listings
                        )
                    }

                    is ListingsState.Error -> ErrorScreen(innerPadding)
                }
            })
    }
}