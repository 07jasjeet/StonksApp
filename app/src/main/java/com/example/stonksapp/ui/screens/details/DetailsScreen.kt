package com.example.stonksapp.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stonksapp.ui.theme.StonksAppTheme
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
    DetailsScreen(uiState = uiState, onBack = onBack)
}

@Composable
fun DetailsScreen(
    uiState: DetailsUiState,
    onBack: () -> Unit
) {
    Column {
        
    }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    StonksAppTheme {
        DetailsScreen(
            uiState = DetailsUiState(),
            onBack = {}
        )
    }
}