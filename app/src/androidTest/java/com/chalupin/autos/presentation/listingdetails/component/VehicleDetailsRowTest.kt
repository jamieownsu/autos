package com.chalupin.autos.presentation.listingdetails.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chalupin.autos.R
import com.chalupin.autos.TestActivity
import com.chalupin.autos.domain.entity.DealerEntity
import com.chalupin.autos.domain.entity.ImagesEntity
import com.chalupin.autos.domain.entity.ListingEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class VehicleDetailsRowTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<TestActivity>()

    private fun createTestListing(): ListingEntity {
        return ListingEntity(
            dealerEntity = DealerEntity(city = "TestCity", state = "TS", phone = "123-456-7890"),
            vin = "TESTVIN123",
            year = 2020,
            make = "TestMake",
            model = "TestModel",
            mileage = 50000,
            currentPrice = 25000F,
            imageCount = 1,
            imagesEntity = ImagesEntity(large = emptyList()),
            exteriorColor = "TestExterior",
            interiorColor = "TestInterior",
            engine = "TestEngine",
            driveType = "TestDrive",
            transmission = "TestTransmission",
            fuel = "TestFuel",
            bodyType = "TestBody"
        )
    }

    @Test
    fun vehicleDetailsRow_displaysAllLabelsAndValues() {
        val listing = createTestListing()

        composeTestRule.setContent {
            MaterialTheme {
                VehicleDetailsRow(listingEntity = listing)
            }
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.vehicle_info))
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.location))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.exterior_color))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.interior_color))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.drive_type))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.transmission))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.body_style))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.engine))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.fuel))
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.city_state,
                listing.dealerEntity.city,
                listing.dealerEntity.state
            )
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(listing.exteriorColor).assertIsDisplayed()
        composeTestRule.onNodeWithText(listing.interiorColor).assertIsDisplayed()
        composeTestRule.onNodeWithText(listing.driveType).assertIsDisplayed()
        composeTestRule.onNodeWithText(listing.transmission).assertIsDisplayed()
        composeTestRule.onNodeWithText(listing.bodyType).assertIsDisplayed()
        composeTestRule.onNodeWithText(listing.engine).assertIsDisplayed()
        composeTestRule.onNodeWithText(listing.fuel).assertIsDisplayed()
    }

    @Test
    fun vehicleDetailsRow_displaysCorrectCityAndStateFormat() {
        val listing = createTestListing().copy(
            dealerEntity = DealerEntity(city = "AnotherCity", state = "AS", phone = "111-222-3333")
        )

        composeTestRule.setContent {
            MaterialTheme {
                VehicleDetailsRow(listingEntity = listing)
            }
        }

        val expectedLocationText =
            composeTestRule.activity.getString(R.string.city_state, "AnotherCity", "AS")
        composeTestRule.onNodeWithText(expectedLocationText).assertIsDisplayed()
    }
}