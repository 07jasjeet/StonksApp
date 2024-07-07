package com.example.stonksapp.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stonksapp.data.StockOverview
import com.example.stonksapp.data.StockType
import com.example.stonksapp.ui.theme.StonksAppTheme

@Composable
fun StockOverviewCard(
    modifier: Modifier = Modifier,
    stockType: StockType,
    stockOverview: StockOverview,
) {
    BoxWithConstraints(
        modifier = modifier
            .aspectRatio(1f)
            .fillMaxSize()
    ) {
        val side = maxWidth
        Column(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    width = 1.dp,
                    color = StonksAppTheme.colorScheme.hint,
                    shape = RoundedCornerShape(side / 18)
                )
                .padding(side / 18),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TextStonks(
                text = stockOverview.ticker,
                fontWeight = FontWeight.Bold,
                fontSize = (side.value / 8).sp
            )

            VerticalSpacer(side / 4)

            TextStonks(
                text = "$${stockOverview.price}",
                fontWeight = FontWeight.Bold,
                fontSize = (side.value / 14).sp
            )

            VerticalSpacer(side / 30)

            Row(verticalAlignment = Alignment.CenterVertically) {
                val color = remember {
                    if (stockType == StockType.Gainer) Color(0xFF00AD00) else Color.Red
                }
                TextStonks(
                    text = "${if (stockType == StockType.Gainer) "+" else "-"}${stockOverview.changePercentage}",
                    color = color,
                    fontWeight = FontWeight.Bold,
                    fontSize = (side.value / 14).sp
                )
                Icon(
                    modifier = Modifier.size(side / 10),
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = null,
                    tint = color
                )
            }

            val totalText = remember {
                if (stockType == StockType.Gainer) {
                    "Total gain: ${stockOverview.changeAmount}"
                } else {
                    "Total loss: ${stockOverview.changeAmount}"
                }
            }

            TextStonks(
                text = totalText,
                color = StonksAppTheme.colorScheme.hint,
                fontSize = (side.value / 16).sp
            )

            TextStonks(
                text = "Volume traded: ${stockOverview.volume} shares",
                color = StonksAppTheme.colorScheme.hint,
                fontSize = (side.value / 16).sp,
            )
        }
    }

}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun StockOverviewCardPreview() {
    StonksAppTheme {
        /*
            "ticker": "SHOTW",
            "price": "0.34",
            "change_amount": "0.16",
            "change_percentage": "88.8889%",
            "volume": "1597"
         */
        Surface {
            StockOverviewCard(
                modifier = Modifier.padding(16.dp),
                stockType = StockType.Gainer,
                stockOverview = StockOverview(
                    ticker = "SHOTW",
                    price = "0.34",
                    changeAmount = "0.16",
                    changePercentage = "88.8889%",
                    volume = "1597"
                )
            )
        }
    }
}