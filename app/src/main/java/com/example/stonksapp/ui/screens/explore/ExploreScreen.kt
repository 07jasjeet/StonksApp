package com.example.stonksapp.ui.screens.explore

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stonksapp.data.StockOverview
import com.example.stonksapp.data.StockType
import com.example.stonksapp.data.TopGainersLosers
import com.example.stonksapp.ui.components.ErrorBar
import com.example.stonksapp.ui.components.StockOverviewCard
import com.example.stonksapp.ui.components.TextStonks
import com.example.stonksapp.ui.components.VerticalSpacer
import com.example.stonksapp.ui.screens.details.MainContent
import com.example.stonksapp.ui.theme.StonksAppTheme
import com.example.stonksapp.utils.Mock
import com.example.stonksapp.utils.Resource
import com.example.stonksapp.viewmodel.ExploreUiState
import com.example.stonksapp.viewmodel.ExploreViewModel

@Composable
fun ExploreScreen(
    scrollRequestState: Boolean,
    scrollToTop: (suspend () -> Unit) -> Unit,
    viewModel: ExploreViewModel = hiltViewModel(),
    onStonkClick: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    ExploreScreen(
        uiState = uiState,
        onErrorShown = { viewModel.clearErrorFlow() },
        onStonkClick = onStonkClick,
        scrollRequestState = scrollRequestState,
        onScrollToTop = scrollToTop
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreScreen(
    uiState: ExploreUiState,
    onScrollToTop: (suspend () -> Unit) -> Unit,
    scrollRequestState: Boolean,
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

        when (uiState.topGainersLosers.status) {
            Resource.Status.LOADING -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = StonksAppTheme.colorScheme.lbSignature)
                }
            }
            Resource.Status.FAILED -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    TextStonks(
                        modifier = Modifier.padding(16.dp),
                        text = "Please try again later.",
                        color = StonksAppTheme.colorScheme.hint,
                        textAlign = TextAlign.Center
                    )
                }
            }
            else -> {
                HorizontalPager(state = pagerState) { index ->
                    if (index == 0) {
                        // Top Gainers
                        StocksList(
                            stockType = StockType.Gainer,
                            stockOverviewList = uiState.topGainersLosers.data?.topGainers ?: emptyList(),
                            onStonkClick = onStonkClick,
                            scrollRequestState = scrollRequestState,
                            onScrollToTop = onScrollToTop
                        )
                    } else {
                        // Top Losers
                        StocksList(
                            stockType = StockType.Loser,
                            stockOverviewList = uiState.topGainersLosers.data?.topLosers ?: emptyList(),
                            onStonkClick = onStonkClick,
                            scrollRequestState = scrollRequestState,
                            onScrollToTop = onScrollToTop
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StocksList(
    modifier: Modifier = Modifier,
    stockType: StockType,
    stockOverviewList: List<StockOverview>,
    scrollRequestState: Boolean,
    onScrollToTop: (suspend () -> Unit) -> Unit,
    onStonkClick: (String) -> Unit
) {
    val gridState = rememberLazyGridState()
    LaunchedEffect(key1 = scrollRequestState) {
        if (scrollRequestState) {
            onScrollToTop {
                gridState.animateScrollToItem(0)
            }
        }
    }

    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        state = gridState,
        columns = GridCells.Fixed(2),
    ) {
        items(count = 2) {
            VerticalSpacer(height = 8.dp)
        }

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
                    topGainersLosers = Resource.success(TopGainersLosers(Mock))
                ),
                onStonkClick = {},
                onErrorShown = {},
                onScrollToTop = {},
                scrollRequestState = false
            )
        }
    }
}