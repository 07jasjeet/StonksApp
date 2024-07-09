package com.example.stonksapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.stonksapp.ui.theme.StonksAppTheme

@Composable
fun TitleSubtitleText(title: String, subtitle: String?) {
    Column {
        WeekHighLowText(
            title = title,
            value = subtitle
        )
    }
}

@Composable
fun ColumnScope.WeekHighLowText(title: String, value: String?) {
    TextStonks(
        text = title,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        color = StonksAppTheme.colorScheme.hint,
    )

    TextStonks(
        text = "$${value ?: "NA"}",
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
    )
}
