package com.example.stonksapp.ui.screens.search

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.platform.WindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stonksapp.data.Match
import com.example.stonksapp.ui.components.ErrorBar
import com.example.stonksapp.ui.components.SearchMatchCard
import com.example.stonksapp.ui.theme.StonksAppTheme
import com.example.stonksapp.utils.ResponseError
import com.example.stonksapp.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    isActive: Boolean,
    navigateToDetails: (symbol: String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
    deactivate: () -> Unit
) {
    AnimatedVisibility(
        visible = isActive,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        val uiState: SearchUiState by viewModel.uiState.collectAsState()

        SearchScreen(
            uiState = uiState,
            onDismiss = {
                deactivate()
                viewModel.clearUi()
            },
            onQueryChange = { query -> viewModel.updateQueryFlow(query) },
            onClick = { match ->
                match.symbol?.let { navigateToDetails(it) }
                deactivate()
                viewModel.clearUi()
            },
            onClear = { viewModel.clearUi() },
            onErrorShown = { viewModel.clearErrorFlow() },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreen(
    uiState: SearchUiState,
    onDismiss: () -> Unit,
    onQueryChange: (String) -> Unit,
    /** Must return if the operation was successful.*/
    onClick: (Match) -> Unit,
    onClear: () -> Unit,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    onSearch: (String) -> Unit = {
        keyboardController?.hide()
    },
    onErrorShown: () -> Unit,
    focusRequester: FocusRequester = remember { FocusRequester() },
    window: WindowInfo = LocalWindowInfo.current
) {
    // Used for initial window focus.
    LaunchedEffect(window){
        snapshotFlow { window.isWindowFocused }.collect { isWindowFocused ->
            if (isWindowFocused){
                focusRequester.requestFocus()
                keyboardController?.show()
            }
        }
    }

    SearchBar(
        modifier = Modifier
            .focusRequester(focusRequester)
            .statusBarsPadding()
            .navigationBarsPadding(),
        query = uiState.query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = true,
        onActiveChange = { isActive ->
            if (!isActive)
                onDismiss()
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        keyboardController?.hide()
                        onDismiss()
                    },
                contentDescription = "Search users",
                tint = StonksAppTheme.colorScheme.hint
            )
        },
        trailingIcon = {
            Icon(imageVector = Icons.Rounded.Clear,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        onClear()
                        keyboardController?.show()
                    },
                contentDescription = "Close Search",
                tint = StonksAppTheme.colorScheme.hint
            )
        },
        placeholder = {
            Text(text = "Search users", color = MaterialTheme.colorScheme.onSurface.copy(0.5f))
        },
        colors = SearchBarDefaults.colors(
            containerColor = StonksAppTheme.colorScheme.background,
            dividerColor = StonksAppTheme.colorScheme.text,
            inputFieldColors = SearchBarDefaults.inputFieldColors(
                focusedPlaceholderColor = Color.Unspecified,
                focusedTextColor = StonksAppTheme.colorScheme.text,
                cursorColor = StonksAppTheme.colorScheme.lbSignatureInverse,
            )
        ),
    ) {

        Column(
            modifier = Modifier
                .pointerInput(key1 = "Keyboard"){
                    // Tap to hide keyboard.
                    detectTapGestures {
                        keyboardController?.hide()
                    }
                }
        ) {

            // Error bar for showing errors
            ErrorBar(uiState.error, onErrorShown)

            // Main Content
            StockList(uiState, onClick)
        }
    }
}

@Composable
private fun StockList(
    uiState: SearchUiState,
    onClick: (Match) -> Unit
) {
    LazyColumn(contentPadding = PaddingValues(StonksAppTheme.paddings.lazyListAdjacent)) {
        items(uiState.matches) { match  ->
            SearchMatchCard(match)
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
private fun SearchScreenPreview() {
    StonksAppTheme {
        SearchScreen(
            uiState = SearchUiState(
                query = "Jasjeet",
                matches = listOf(),
                error = ResponseError.DOES_NOT_EXIST
            ),
            onDismiss = {},
            onQueryChange = {},
            onClick = { _ -> },
            onClear = {},
            onErrorShown = {}
        )
    }
}