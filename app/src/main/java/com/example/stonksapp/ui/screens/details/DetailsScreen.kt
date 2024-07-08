package com.example.stonksapp.ui.screens.details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stonksapp.data.CompanyOverview
import com.example.stonksapp.ui.components.ErrorBar
import com.example.stonksapp.ui.components.TextStonks
import com.example.stonksapp.ui.components.VerticalSpacer
import com.example.stonksapp.ui.theme.StonksAppTheme
import com.example.stonksapp.utils.Mock
import com.example.stonksapp.utils.Resource
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
    Column {
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
                    TextStonks(text = "Loading")
                }
            }
            Resource.Status.FAILED -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    TextStonks(text = "Failed")
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.85f)) {
                TextStonks(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                TextStonks(
                    text ="$symbol, $assetType",
                    fontSize = 14.sp,
                    color = StonksAppTheme.colorScheme.hint,
                    fontWeight = FontWeight.Medium
                )
                TextStonks(
                    text = exchange,
                    fontSize = 14.sp,
                    color = StonksAppTheme.colorScheme.hint,
                    fontWeight = FontWeight.Medium
                )
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                TextStonks(
                    modifier = Modifier

                        .align(alignment = Alignment.End),
                    text = "$${bookValue}",
                    fontSize = 12.sp,
                    letterSpacing = 0.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        VerticalSpacer(height = 16.dp)

        Column(
            modifier = Modifier
                .border(1.5.dp, StonksAppTheme.colorScheme.hint, RoundedCornerShape(16.dp))
        ) {
            TextStonks(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                text = "About $name",
                fontWeight = FontWeight.Black
            )

            HorizontalDivider(thickness = 1.5.dp, color = StonksAppTheme.colorScheme.hint)

            Column(
                modifier = Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 14.dp
                )
            ) {
                TextStonks(
                    text = description,
                    fontSize = 14.5.sp,
                    fontWeight = FontWeight.Medium,
                    color = StonksAppTheme.colorScheme.text.copy(alpha = 0.7f),
                    lineHeight = 17.sp
                )

                
            }
        }
    }
}


@Preview(device = Devices.PHONE)
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