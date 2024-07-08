package com.example.stonksapp.ui.screens.explore

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stonksapp.data.StockOverview
import com.example.stonksapp.data.StockType
import com.example.stonksapp.ui.components.ErrorBar
import com.example.stonksapp.ui.components.StockOverviewCard
import com.example.stonksapp.ui.components.VerticalSpacer
import com.example.stonksapp.ui.theme.StonksAppTheme
import com.example.stonksapp.utils.Mock
import com.example.stonksapp.viewmodel.ExploreUiState
import com.example.stonksapp.viewmodel.ExploreViewModel

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = hiltViewModel(),
    onStonkClick: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    ExploreScreen(
        uiState = uiState,
        onErrorShown = { viewModel.clearErrorFlow() },
        onStonkClick = onStonkClick
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreScreen(
    uiState: ExploreUiState,
    onErrorShown: () -> Unit,
    onStonkClick: (String) -> Unit
) {
    val pagerState = rememberPagerState { 2 }
    Column {
        ErrorBar(
            error = uiState.error,
            onErrorShown = onErrorShown
        )

        ExploreTabs(selectedTabIndex = pagerState.currentPage) {
            pagerState.animateScrollToPage(it)
        }

        HorizontalPager(state = pagerState) { index ->
            if (index == 0) {
                // Top Gainers
                StocksList(
                    stockType = StockType.Gainer,
                    stockOverviewList = uiState.topGainers,
                    onStonkClick = onStonkClick
                )
            } else {
                // Top Losers
                StocksList(
                    stockType = StockType.Loser,
                    stockOverviewList = uiState.topLosers,
                    onStonkClick = onStonkClick
                )
            }
        }
    }
}

@Composable
fun StocksList(
    modifier: Modifier = Modifier,
    stockType: StockType,
    stockOverviewList: List<StockOverview>,
    onStonkClick: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
    ) {
        items(stockOverviewList) {
            StockOverviewCard(
                modifier = Modifier
                    .padding(
                        horizontal = StonksAppTheme.paddings.horizontal,
                        vertical = StonksAppTheme.paddings.vertical
                    )
                    .clickable { onStonkClick(it.ticker) },
                stockType = stockType,
                stockOverview = it
            )
        }
    }
}


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ExplorePreview() {
    StonksAppTheme {
        Surface {
            ExploreScreen(
                uiState = ExploreUiState(
                    topGainers = List(20) {
                        StockOverview(Mock)
                    },
                    topLosers = List(20) {
                        StockOverview(Mock)
                    }
                ),
                onStonkClick = {},
                onErrorShown = {}
            )
        }
    }
}