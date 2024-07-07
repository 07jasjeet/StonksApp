package com.example.stonksapp.ui.screens.explore.navigation

import com.example.stonksapp.ui.navigation.NavigationDestination

enum class ExploreDestination(
    override val displayName: String,
    override val topBarName: String = displayName
): NavigationDestination {
    TOP_GAINERS("Top Gainers"),
    TOP_LOSERS("Top Losers");

    override val route: String = name

    companion object {
        fun String.toExploreDestination(): ExploreDestination =
            when (this) {
                TOP_GAINERS.name -> TOP_GAINERS
                TOP_LOSERS.name -> TOP_LOSERS
                else -> throw IllegalArgumentException("$this cannot be converted to ExploreDestination.")
            }
    }
}