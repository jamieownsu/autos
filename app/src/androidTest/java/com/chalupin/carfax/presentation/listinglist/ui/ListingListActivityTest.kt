package com.chalupin.carfax.presentation.listinglist.ui

import AppNavigationHost
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chalupin.carfax.TestActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ListingListActivityTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<TestActivity>()

    @Test
    fun listingListActivity_launchesAndDisplaysContent() {
        composeTestRule.setContent {
            com.chalupin.carfax.core.theme.CarFaxTheme {
                AppNavigationHost(navController = androidx.navigation.compose.rememberNavController())
            }
        }
        composeTestRule.onNodeWithText("CARFAX")
            .assertIsDisplayed()
    }
}