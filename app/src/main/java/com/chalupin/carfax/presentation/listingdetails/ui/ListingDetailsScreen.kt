package com.chalupin.carfax.presentation.listingdetails.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.chalupin.carfax.presentation.listingdetails.component.ListingDetailsColumn
import com.chalupin.carfax.presentation.listingdetails.viewmodel.ListingDetailViewModel
import com.chalupin.carfax.presentation.shared.ErrorScreen
import com.chalupin.carfax.presentation.shared.LoadingScreen

@Composable
fun ListingDetailScreen(
    viewModel: ListingDetailViewModel = hiltViewModel(),
) {
    val listingDetails by viewModel.listingDetails.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    Scaffold { innerPadding ->
        if (isLoading) {
            LoadingScreen(innerPadding)
        } else if (error != null) {
            ErrorScreen(innerPadding)
        } else {
            listingDetails?.let {
                ListingDetailsColumn(it)
            }
        }
    }
}