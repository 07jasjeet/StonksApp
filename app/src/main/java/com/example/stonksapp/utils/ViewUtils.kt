package com.example.stonksapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@Composable
fun getScreenSize() : DpSize {
    val width = LocalConfiguration.current.screenHeightDp.dp
    val height = LocalConfiguration.current.screenWidthDp.dp

    return DpSize(width, height)
}