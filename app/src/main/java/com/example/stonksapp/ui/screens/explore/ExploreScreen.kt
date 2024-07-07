package com.example.stonksapp.ui.screens.explore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stonksapp.data.StockOverview
import com.example.stonksapp.ui.theme.StonksAppTheme
import com.example.stonksapp.viewmodel.ExploreUiState
import com.example.stonksapp.viewmodel.ExploreViewModel

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    ExploreScreen(uiState)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreScreen(uiState: ExploreUiState) {
    val pagerState = rememberPagerState { 2 }
    Column {
        ExploreTabs(selectedTabIndex = pagerState.currentPage) {
            pagerState.animateScrollToPage(it)
        }

        HorizontalPager(state = pagerState) { index ->
            if (index == 0) {
                // Top Gainers
                StocksList(uiState.topGainers)
            } else {
                // Top Losers
                StocksList(uiState.topLosers)
            }
        }
    }
}

@Composable
fun StocksList(topGainers: List<StockOverview>) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2)
    ) {
        items(topGainers) {
            Text(
                text = it.ticker.toString(),
                color = StonksAppTheme.colorScheme.text
            )
        }
    }
}



@Preview
@Composable
private fun ExplorePreview() {
    StonksAppTheme {
        ExploreScreen(
            uiState = ExploreUiState()
        )
    }
}