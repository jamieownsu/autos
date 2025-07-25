package com.chalupin.carfax.presentation.listinglist.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chalupin.carfax.R
import com.chalupin.carfax.presentation.listingdetails.component.CallDealerButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingListAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        title = {
            Text(
                stringResource(id = R.string.app_name).uppercase(),
                modifier = Modifier.padding(start = 32.dp)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewListingListAppBar() {
    MaterialTheme {
        ListingListAppBar()
    }
}