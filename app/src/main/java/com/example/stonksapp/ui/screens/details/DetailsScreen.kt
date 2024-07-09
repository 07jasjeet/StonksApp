package com.example.stonksapp.ui.screens.details

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stonksapp.data.CompanyOverview
import com.example.stonksapp.ui.components.ErrorBar
import com.example.stonksapp.ui.components.HorizontalSpacer
import com.example.stonksapp.ui.components.TextStonks
import com.example.stonksapp.ui.components.VerticalSpacer
import com.example.stonksapp.ui.theme.LocalIsDarkTheme
import com.example.stonksapp.ui.theme.StonksAppTheme
import com.example.stonksapp.utils.Mock
import com.example.stonksapp.utils.Resource
import com.example.stonksapp.viewmodel.DetailsViewModel
import kotlin.math.pow

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
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        ErrorBar(
            error = uiState.error,
            onErrorShown = onErrorShown
        )

        when (uiState.resource.status) {
            Resource.Status.LOADING -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = StonksAppTheme.colorScheme.lbSignature)
                }
            }
            Resource.Status.FAILED -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    TextStonks(
                        modifier = Modifier.padding(16.dp),
                        text = "Please try again later.",
                        color = StonksAppTheme.colorScheme.hint,
                        textAlign = TextAlign.Center
                    )
                }
            }
            else -> {
                uiState.resource.data?.MainContent()
            }
        }
    }
}


@Composable
fun CompanyOverview.MainContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = StonksAppTheme.paddings.vertical,
                horizontal = StonksAppTheme.paddings.horizontal
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.85f)) {
                TextStonks(
                    text = name ?: "--Name unavailable--",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                TextStonks(
                    text ="${symbol ?: "--Symbol unavailable--"}, ${assetType ?: "--Type unavailable--"}",
                    fontSize = 14.sp,
                    color = StonksAppTheme.colorScheme.hint,
                    fontWeight = FontWeight.Medium
                )
                TextStonks(
                    text = exchange ?: "--Exchange unavailable--",
                    fontSize = 14.sp,
                    color = StonksAppTheme.colorScheme.hint,
                    fontWeight = FontWeight.Medium
                )
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                TextStonks(
                    modifier = Modifier.align(alignment = Alignment.End),
                    text = "$${bookValue ?: "--Book value unavailable--"}",
                    fontSize = 12.sp,
                    letterSpacing = 0.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        VerticalSpacer(height = 16.dp)

        Column(
            modifier = Modifier
                .border(1.5.dp, StonksAppTheme.colorScheme.hint.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
        ) {
            TextStonks(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                text = "About ${name ?: "--Name unavailable--"}",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )

            HorizontalDivider(thickness = 1.5.dp, color = StonksAppTheme.colorScheme.hint.copy(alpha = 0.4f))

            Column(
                modifier = Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 14.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextStonks(
                    text = description ?: "--Description unavailable--",
                    fontSize = 14.5.sp,
                    fontWeight = FontWeight.Medium,
                    color = StonksAppTheme.colorScheme.text.copy(alpha = 0.6f),
                    lineHeight = 17.sp
                )

                LazyRow {
                    item {
                        InformationChip("Industry: ${industry ?: "--Industry unavailable--"}")
                    }

                    item {
                        HorizontalSpacer(width = 16.dp)
                    }
                    item {
                        InformationChip("Sector: ${sector ?: "--Sector unavailable--"}")
                    }
                }

                BoxWithConstraints(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .width(maxWidth * 0.265f)
                    ) {
                        WeekHighLowText(
                            title = "52-week-low",
                            value = `52WeekLow`
                        )
                    }

                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(maxWidth * 0.47f),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        val lineColor = StonksAppTheme.colorScheme.hint
                        val textMeasurer = rememberTextMeasurer()
                        val textStyle = LocalTextStyle.current.copy(
                            color = lineColor,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 10.sp
                        )

                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .height(38.dp)
                                .fillMaxWidth()
                                .drawWithCache {
                                    val textLayoutResult = textMeasurer.measure(
                                        text = "Current price: $${analystTargetPrice ?: "NA"}",
                                        style = textStyle
                                    )
                                    val textSize = textLayoutResult.size.toSize()

                                    val max = `52WeekHigh`?.toFloatOrNull() ?: 2f
                                    val min = `52WeekLow`?.toFloatOrNull() ?: 0f
                                    val currentPrice = analystTargetPrice?.toFloatOrNull() ?: 1f
                                    val pricePerPixel = size.width / (max - min)

                                    val pointerCircleRadius = 3.dp.toPx()
                                    val pointerXAxis = (currentPrice - min) * pricePerPixel

                                    onDrawWithContent {
                                        drawText(
                                            textLayoutResult,
                                            topLeft = Offset(
                                                x = (pointerXAxis - textSize.width / 2)
                                                    .coerceIn(0f..(size.width - textSize.width)),
                                                y = 0f
                                            )
                                        )
                                        drawCircle(
                                            color = lineColor,
                                            radius = pointerCircleRadius,
                                            center = Offset(
                                                x = pointerXAxis
                                                    .coerceIn(pointerCircleRadius..(size.width - pointerCircleRadius)),
                                                y = textSize.height + pointerCircleRadius / 2 + 2.dp.toPx()
                                            )
                                        )

                                        drawRoundRect(
                                            color = lineColor,
                                            topLeft = Offset(
                                                x = 0f,
                                                y = textSize.height + pointerCircleRadius * 2 + 2.dp.toPx()
                                            ),
                                            size = Size(size.width, 3.dp.toPx()),
                                            cornerRadius = CornerRadius(3.dp.toPx())
                                        )
                                    }
                                }
                        )
                    }

                    Column(
                        modifier = Modifier
                            .width(maxWidth * 0.265f)
                            .align(Alignment.CenterEnd),
                        horizontalAlignment = Alignment.End
                    ) {
                        WeekHighLowText(
                            title = "52-week-high",
                            value = `52WeekHigh`
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TitleSubtitleText(
                        title = "Market Cap",
                        subtitle = marketCapitalization?.let { convertLargeNumber(it) }
                    )

                    TitleSubtitleText(
                        title = "P/E Ratio",
                        subtitle = pERatio
                    )

                    TitleSubtitleText(
                        title = "Beta",
                        subtitle = beta
                    )

                    TitleSubtitleText(
                        title = "Dividend Yield",
                        subtitle = dividendYield
                    )

                    TitleSubtitleText(
                        title = "Profit Margin",
                        subtitle = profitMargin
                    )
                }
            }
        }
    }
}

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
private fun ColumnScope.WeekHighLowText(title: String, value: String?) {
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


@Preview(device = Devices.PHONE)
@Preview(device = Devices.PHONE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailsScreenPreview() {
    StonksAppTheme {
        Surface {
            DetailsScreen(
                uiState = DetailsUiState(
                    resource = Resource.success(CompanyOverview(Mock)),
                ),
                onBack = {},
                onErrorShown = {}
            )
        }
    }
}