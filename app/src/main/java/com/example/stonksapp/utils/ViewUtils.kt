package com.example.stonksapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlin.math.pow

@Composable
fun getScreenSize() : DpSize {
    val width = LocalConfiguration.current.screenHeightDp.dp
    val height = LocalConfiguration.current.screenWidthDp.dp

    return DpSize(width, height)
}

fun convertLargeNumber(numberStr: String): String {
    val suffixes = arrayOf("", "K", "M", "B", "T")
    val number = numberStr.toLongOrNull() ?: return numberStr

    for (i in suffixes.indices) {
        val divisor = 1000.0.pow(i).toLong()
        if (number < 1000.0.pow(i + 1)) {
            val result = number.toDouble() / divisor
            val formattedNumber = if (result % 1.0 == 0.0) result.toInt().toString() else String.format("%.1f", result)
            return "$formattedNumber ${suffixes[i]}".trim()
        }
    }
    return numberStr
}