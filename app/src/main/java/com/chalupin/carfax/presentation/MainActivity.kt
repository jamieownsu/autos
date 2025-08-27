package com.chalupin.carfax.presentation

import AppNavigationHost
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chalupin.carfax.core.theme.CarFaxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarFaxTheme {
                val navController: NavHostController = rememberNavController()
                AppNavigationHost(navController = navController)
            }
        }
    }
}