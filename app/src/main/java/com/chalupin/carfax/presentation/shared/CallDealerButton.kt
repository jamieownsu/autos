package com.chalupin.carfax.presentation.shared

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.chalupin.carfax.R

@Composable
fun CallDealerButton(phoneNumber: String, isDetailsScreen: Boolean = false) {
    val context = LocalContext.current
    val callPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            makePhoneCall(context, phoneNumber)
        } else {
            Toast.makeText(
                context,
                context.getString(R.string.call_perm_denied),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    if (isDetailsScreen) {
        Button(
            onClick = {
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) -> {
                        makePhoneCall(context, phoneNumber)
                    }

                    else -> {
                        callPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                    }
                }
            },
            shape = RectangleShape,
            modifier = Modifier.fillMaxWidth(.95f)
        ) {
            Text(
                stringResource(id = R.string.call_dealer),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    } else {
        TextButton(
            onClick = {
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) -> {
                        makePhoneCall(context, phoneNumber)
                    }

                    else -> {
                        callPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .size(36.dp)
                .padding(bottom = 4.dp)
        ) {
            Text(
                stringResource(id = R.string.call_dealer),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

fun makePhoneCall(context: Context, phoneNumber: String) {
    try {
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = "tel:$phoneNumber".toUri()
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        if (e is SecurityException) {
            Log.e("CallDealerButton", "Permission error: ${e.message}", e)
        } else {
            Log.e("CallDealerButton", "Call failed: ${e.message}", e)
        }
        Toast.makeText(
            context,
            context.getString(R.string.call_failed, e.message),
            Toast.LENGTH_LONG
        ).show()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCallDealerButton() {
    MaterialTheme {
        CallDealerButton(
            "555-555-5555",
            false
        )
    }
}