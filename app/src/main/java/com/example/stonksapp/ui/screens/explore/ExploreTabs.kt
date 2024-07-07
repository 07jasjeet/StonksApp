package com.example.stonksapp.ui.screens.explore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.stonksapp.ui.components.TabRow
import com.example.stonksapp.ui.screens.explore.navigation.ExploreDestination

@Composable
fun ExploreTabs(
    modifier: Modifier = Modifier,
    selectedTabIndex: Int = 0,
    onTabClick: suspend (index: Int) -> Unit
) {
    val tabList = remember {
        listOf(
            ExploreDestination.TOP_GAINERS,
            ExploreDestination.TOP_LOSERS,
        )
    }

    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex,
        tabs = remember { tabList.map { it.displayName } },
        onClick = onTabClick
    )
}