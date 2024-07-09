package com.example.stonksapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stonksapp.ui.theme.StonksAppTheme

@Composable
fun InformationChip(text: String) {
    Box(
        modifier = Modifier
            .background(
                color = StonksAppTheme.colorScheme.infoChip,
                shape = CircleShape
            )
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = StonksAppTheme.colorScheme.onInfoChip
        )
    }
}