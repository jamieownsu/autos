import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chalupin.autos.core.navigation.NavRoutes
import com.chalupin.autos.presentation.listingdetails.ui.ListingDetailScreen
import com.chalupin.autos.presentation.listinglist.ui.ListingListScreen


@Composable
fun AppNavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.LISTING_LIST,
    ) {
        composable(NavRoutes.LISTING_LIST) {
            ListingListScreen(
                navController = navController,
                viewModel = hiltViewModel(),
            )
        }
        composable(
            route = "${NavRoutes.LISTING_DETAIL}/{${NavRoutes.LISTING_VIN_ARG}}",
            arguments = listOf(navArgument(NavRoutes.LISTING_VIN_ARG) {
                type = NavType.StringType
            }),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(durationMillis = 400)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(600)
                )
            },
        ) {
            ListingDetailScreen(
                viewModel = hiltViewModel(),
            )
        }
    }
}


