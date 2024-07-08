package com.example.stonksapp.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stonksapp.data.CompanyOverview
import com.example.stonksapp.ui.components.ErrorBar
import com.example.stonksapp.ui.theme.StonksAppTheme
import com.example.stonksapp.utils.Mock
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
    }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    StonksAppTheme {
        DetailsScreen(
            uiState = DetailsUiState(
                companyOverview = CompanyOverview(Mock)
            ),
            onBack = {},
            onErrorShown = {}
        )
    }
}