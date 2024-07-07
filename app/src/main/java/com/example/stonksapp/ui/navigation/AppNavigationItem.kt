package com.example.stonksapp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import com.example.stonksapp.R

sealed class AppNavigationItem(
    val route: String,
    @DrawableRes val iconUnselected: Int,
    @DrawableRes val iconSelected: Int,
    val title: String
) {
    data object Explore: AppNavigationItem("explore", 0, 0, "Explore")

    data object Details : AppNavigationItem("details/{symbol}", 0, 0, "Details") {
        const val SYMBOL = "symbol"
        fun createRoute(symbol: String) = "details/$symbol"
    }
}