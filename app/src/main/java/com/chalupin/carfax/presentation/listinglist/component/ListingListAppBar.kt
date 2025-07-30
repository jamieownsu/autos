package com.chalupin.carfax.presentation.listinglist.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chalupin.carfax.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingListAppBar(isOffline: Boolean, snackBarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()

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
        },
        actions = {
            val offlineMessage = stringResource(R.string.offline_message)
            val dismiss = stringResource(R.string.dismiss)
            if (isOffline)
                IconButton(onClick = {
                    scope.launch {
                        snackBarHostState.showSnackbar(
                            message = offlineMessage,
                            actionLabel = dismiss,
                            duration = SnackbarDuration.Long
                        )
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.WifiOff,
                        contentDescription = "WifiOff",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewListingListAppBar() {
    MaterialTheme {
        ListingListAppBar(isOffline = true, snackBarHostState = SnackbarHostState())
    }
}