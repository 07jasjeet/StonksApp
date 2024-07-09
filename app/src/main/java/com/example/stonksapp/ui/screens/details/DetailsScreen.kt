package com.example.stonksapp.ui.screens.details

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stonksapp.data.CompanyOverview
import com.example.stonksapp.ui.components.ErrorBar
import com.example.stonksapp.ui.components.HorizontalSpacer
import com.example.stonksapp.ui.components.InformationChip
import com.example.stonksapp.ui.components.TextStonks
import com.example.stonksapp.ui.components.TitleSubtitleText
import com.example.stonksapp.ui.components.VerticalSpacer
import com.example.stonksapp.ui.theme.StonksAppTheme
import com.example.stonksapp.utils.Mock
import com.example.stonksapp.utils.Resource
import com.example.stonksapp.utils.convertLargeNumber
import com.example.stonksapp.viewmodel.DetailsViewModel

@Composable
fun DetailsScreen(
    symbol: String,
    viewModel: DetailsViewModel = hiltViewModel(
        creationCallback = { factory: DetailsViewModel.ViewModelFactory ->
            factory.create(symbol)
        }
    ),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    DetailsScreen(
        uiState = uiState,
        onBack = onBack,
        onErrorShown = {
            viewModel.clearErrorFlow()
        }
    )
}

@Composable
fun DetailsScreen(
    uiState: DetailsUiState,
    onErrorShown: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ErrorBar(
            error = uiState.error,
            onErrorShown = onErrorShown
        )

        when (uiState.resource.status) {
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
                uiState.resource.data?.MainContent()
            }
        }
    }
}


@Composable
fun CompanyOverview.MainContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = StonksAppTheme.paddings.vertical,
                horizontal = StonksAppTheme.paddings.horizontal
            )
    ) {
        DetailsHeader()

        VerticalSpacer(height = 16.dp)

        Column(
            modifier = Modifier
                .border(1.5.dp, StonksAppTheme.colorScheme.hint.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
        ) {
            TextStonks(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                text = "About ${name ?: "--Name unavailable--"}",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )

            HorizontalDivider(thickness = 1.5.dp, color = StonksAppTheme.colorScheme.hint.copy(alpha = 0.4f))

            Column(
                modifier = Modifier
                    .padding(
                        vertical = 8.dp,
                        horizontal = 14.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextStonks(
                    text = description ?: "--Description unavailable--",
                    fontSize = 14.5.sp,
                    fontWeight = FontWeight.Medium,
                    color = StonksAppTheme.colorScheme.text.copy(alpha = 0.6f),
                    lineHeight = 17.sp
                )

                LazyRow {
                    item {
                        InformationChip("Industry: ${industry ?: "--Industry unavailable--"}")
                    }

                    item {
                        HorizontalSpacer(width = 16.dp)
                    }
                    item {
                        InformationChip("Sector: ${sector ?: "--Sector unavailable--"}")
                    }
                }

                WeekHighLowLineGraph()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TitleSubtitleText(
                        title = "Market Cap",
                        subtitle = marketCapitalization?.let { convertLargeNumber(it) }
                    )

                    TitleSubtitleText(
                        title = "P/E Ratio",
                        subtitle = pERatio
                    )

                    TitleSubtitleText(
                        title = "Beta",
                        subtitle = beta
                    )

                    TitleSubtitleText(
                        title = "Dividend Yield",
                        subtitle = dividendYield
                    )

                    TitleSubtitleText(
                        title = "Profit Margin",
                        subtitle = profitMargin
                    )
                }
            }
        }
    }
}

@Composable
private fun CompanyOverview.DetailsHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.fillMaxWidth(0.85f)) {
            TextStonks(
                text = name ?: "--Name unavailable--",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            TextStonks(
                text = "${symbol ?: "--Symbol unavailable--"}, ${assetType ?: "--Type unavailable--"}",
                fontSize = 14.sp,
                color = StonksAppTheme.colorScheme.hint,
                fontWeight = FontWeight.Medium
            )
            TextStonks(
                text = exchange ?: "--Exchange unavailable--",
                fontSize = 14.sp,
                color = StonksAppTheme.colorScheme.hint,
                fontWeight = FontWeight.Medium
            )
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            TextStonks(
                modifier = Modifier.align(alignment = Alignment.End),
                text = "$${bookValue ?: "--Book value unavailable--"}",
                fontSize = 12.sp,
                letterSpacing = 0.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(device = Devices.PHONE)
@Preview(device = Devices.PHONE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailsScreenPreview() {
    StonksAppTheme {
        Surface {
            DetailsScreen(
                uiState = DetailsUiState(
                    resource = Resource.success(CompanyOverview(Mock)),
                ),
                onBack = {},
                onErrorShown = {}
            )
        }
    }
}