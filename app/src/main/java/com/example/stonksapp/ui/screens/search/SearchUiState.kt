package com.example.stonksapp.ui.screens.search

import com.example.stonksapp.data.Match
import com.example.stonksapp.utils.ResponseError

data class SearchUiState(
    val query: String,
    val matches: List<Match>,
    val error: ResponseError?
)