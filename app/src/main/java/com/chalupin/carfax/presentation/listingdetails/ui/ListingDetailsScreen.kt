package com.chalupin.carfax.presentation.listingdetails.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.chalupin.carfax.presentation.listingdetails.component.ListingDetailsColumn
import com.chalupin.carfax.presentation.listingdetails.viewmodel.ListingDetailViewModel

@Composable
fun ListingDetailScreen(
    viewModel: ListingDetailViewModel = hiltViewModel(),
    vin: String,
) {
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
            ListingDetailsColumn(it)
        } ?: run {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) { Text(text = "No details found for VIN: $vin") }
        }
    }
}