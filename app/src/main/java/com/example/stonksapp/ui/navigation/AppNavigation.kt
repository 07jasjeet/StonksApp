package com.example.stonksapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stonksapp.ui.screens.details.DetailsScreen
import com.example.stonksapp.ui.screens.explore.ExploreScreen

@Composable
fun AppNavigation(
    navController: NavController = rememberNavController(),
    paddingValues: PaddingValues,
    scrollRequestState: Boolean,
    onScrollToTop: (suspend () -> Unit) -> Unit,
    snackbarState : SnackbarHostState
) {
    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        navController = navController as NavHostController,
        startDestination = AppNavigationItem.Explore.route
    ){
        composable(AppNavigationItem.Explore.route) {
            ExploreScreen {
                navController.navigate(AppNavigationItem.Details.createRoute(it))
            }
        }

        composable(AppNavigationItem.Details.route) {
            val symbol = remember {
                it.arguments?.getString(AppNavigationItem.Details.SYMBOL)
            }
            if (symbol != null) {
                DetailsScreen(symbol = symbol) {
                    navController.popBackStack()
                }
            }
        }
    }
}