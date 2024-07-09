package com.example.stonksapp.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.stonksapp.data.CompanyOverview
import com.example.stonksapp.ui.components.WeekHighLowText
import com.example.stonksapp.ui.theme.StonksAppTheme

@Composable
fun CompanyOverview.WeekHighLowLineGraph() {
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
}