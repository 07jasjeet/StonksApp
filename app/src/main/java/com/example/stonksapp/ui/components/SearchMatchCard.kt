package com.example.stonksapp.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stonksapp.data.Match
import com.example.stonksapp.ui.theme.StonksAppTheme
import com.example.stonksapp.utils.Mock

@Composable
fun SearchMatchCard(
    modifier: Modifier = Modifier,
    onClick: (Match) -> Unit = {},
    match: Match
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = StonksAppTheme.colorScheme.hint,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick(match) }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        TextStonks(
            modifier = Modifier,
            text = match.name,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        TextStonks(
            modifier = Modifier,
            text = match.symbol,
            fontWeight = FontWeight.Bold,
            color = StonksAppTheme.colorScheme.hint,
            fontSize = 16.sp
        )


        VerticalSpacer(height = 8.dp)

        TextStonks(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = StonksAppTheme.colorScheme.hint)) {
                    append("Currency: ")
                }

                withStyle(
                    SpanStyle(
                        color = StonksAppTheme.colorScheme.text,
                        fontWeight = FontWeight.Medium
                    )
                ) {
                    append(match.currency)
                }
            },
            fontSize = 16.sp
        )

        TextStonks(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = StonksAppTheme.colorScheme.lbSignature,
                        fontWeight = FontWeight.Medium
                    )
                ) {
                    append(match.marketOpen)
                }

                withStyle(SpanStyle(color = StonksAppTheme.colorScheme.hint)) {
                    append(" - ")
                }

                withStyle(
                    SpanStyle(
                        color = StonksAppTheme.colorScheme.lbSignatureInverse,
                        fontWeight = FontWeight.Medium
                    )
                ) {
                    append(match.marketClose)
                }
            },
            color = StonksAppTheme.colorScheme.lbSignatureInverse,
            fontSize = 16.sp
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            TextStonks(
                text = match.region,
                color = StonksAppTheme.colorScheme.hint,
                fontSize = 16.sp
            )

            TextStonks(
                text = match.timezone,
                color = StonksAppTheme.colorScheme.hint,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(device = Devices.PIXEL)
@Preview(device = Devices.PIXEL, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SearchMatchCardPreview() {
    StonksAppTheme {
        Surface {
            SearchMatchCard(
                modifier = Modifier.padding(8.dp),
                match = Match(Mock)
            )
        }
    }
}