package com.example.stonksapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.stonksapp.data.Match
import com.example.stonksapp.ui.theme.StonksAppTheme
import com.example.stonksapp.utils.Mock

@Composable
fun SearchMatchCard(match: Match) {

}

@Preview
@Composable
private fun SearchMatchCardPreview() {
    StonksAppTheme {
        SearchMatchCard(match = Match(Mock))
    }
}