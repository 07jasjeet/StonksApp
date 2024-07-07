package com.example.stonksapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.stonksapp.ui.theme.StonksAppTheme
import kotlinx.coroutines.launch

@Composable
fun TabRow(
    modifier: Modifier = Modifier,
    selectedTabIndex: Int,
    tabs: List<String>,
    onClick: suspend (index: Int) -> Unit
) {
    val scope = rememberCoroutineScope()

    TabRow(
        modifier = modifier
            .padding(horizontal = StonksAppTheme.paddings.horizontal)
            .clip(RoundedCornerShape(6.dp)),
        selectedTabIndex = selectedTabIndex,
        containerColor = StonksAppTheme.colorScheme.chipUnselected,
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = StonksAppTheme.colorScheme.text
                )
            }
        },
        divider = {}
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                text = title
            ) {
                scope.launch { onClick(index) }
            }
        }
    }
}

@Composable
fun Tab(selected: Boolean, text: String, onClick: () -> Unit) {
    Tab(
        modifier = Modifier.padding(6.dp),
        selectedContentColor = StonksAppTheme.colorScheme.chipSelected,
        unselectedContentColor = StonksAppTheme.colorScheme.chipUnselected,
        selected = selected,
        onClick = onClick
    ) {
        Text(
            text = text,
            color = StonksAppTheme.colorScheme.text,
            fontWeight = FontWeight.Medium
        )
    }
}

@PreviewLightDark
@Composable
fun LimTabPreview() {
    StonksAppTheme {
        Surface(color = StonksAppTheme.colorScheme.background) {
            TabRow(
                selectedTabIndex = 0,
                tabs = listOf("Top Gainers", "Top Losers")
            ){}
        }
    }
}