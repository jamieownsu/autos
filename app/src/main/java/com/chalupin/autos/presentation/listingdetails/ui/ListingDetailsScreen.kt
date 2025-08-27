package com.chalupin.autos.presentation.listingdetails.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chalupin.autos.presentation.listingdetails.component.ListingDetailsColumn
import com.chalupin.autos.presentation.listingdetails.util.ListingDetailsState
import com.chalupin.autos.presentation.listingdetails.viewmodel.ListingDetailViewModel
import com.chalupin.autos.presentation.shared.ErrorScreen
import com.chalupin.autos.presentation.shared.LoadingScreen

@Composable
fun ListingDetailScreen(
    viewModel: ListingDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold { innerPadding ->
        when (val state = uiState) {
            is ListingDetailsState.Loading -> LoadingScreen(innerPadding)
            is ListingDetailsState.Success -> {
                val listingDetails = state.listingDetails
                ListingDetailsColumn(listingDetails)
            }

            is ListingDetailsState.Error -> ErrorScreen(innerPadding)
        }
    }
}