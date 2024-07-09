package com.example.stonksapp.ui.theme

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.stonksapp.data.Theme
import com.example.stonksapp.repository.preferences.AppPreferences
import com.example.stonksapp.repository.preferences.AppPreferencesImpl

/** ColorScheme for the whole app. */
data class ColorScheme(
    val background: Color,
    val onBackground: Color,
    val nav: Color,
    val level1: Color,
    val level2: Color,
    val lbSignature: Color,
    val lbSignatureSecondary: Color,
    val lbSignatureInverse: Color,
    val onLbSignature: Color,
    val chipUnselected: Color,
    val chipSelected: Color,
    val dialog: Color,
    val tabs: Color,
    val dialogPositiveButtonEnabled: Color = Color(0xFF5DA855),
    val dialogPositiveButtonDisabled: Color = Color(0xFF9EB99C),
    val dialogNegativeButton: Color = Color(0xFF696658),
    val dialogNegativeButtonText: Color = Color.White,
    val text: Color,
    val golden: Color = Color(0xFFF9A825),
    val hint: Color,
    val infoChip: Color,
    val onInfoChip: Color,
)

private val colorSchemeDark = ColorScheme(
    background = app_bg_dark,
    onBackground = Color.White,
    nav = Color.Black,
    level1 = app_bottom_nav_dark,
    level2 = Color(0xFF4E4E4E),
    lbSignature = Color(0xFF9AABD1),
    lbSignatureSecondary = lb_yellow,
    lbSignatureInverse = lb_orange,
    onLbSignature = Color.Black,
    chipUnselected = Color(0xFF1E1E1E),
    chipSelected = Color.Black,
    dialog = Color.Black,
    text = Color.White,
    hint = Color(0xFF9C9C9C),
    tabs = Color(0xFF1E1E1E),
    infoChip = Color(0x805E220C),
    onInfoChip = Color(0xFFFDAC93)
)

private val colorSchemeLight = ColorScheme(
    background = app_bg_day,
    onBackground = Color.Black,
    nav = Color.White,
    level1 = app_bottom_nav_day,
    level2 = Color(0xFF1E1E1E),
    lbSignature = lb_purple,
    lbSignatureSecondary = lb_yellow,
    lbSignatureInverse = Color(0xFFE5743E),
    onLbSignature = Color.White,
    chipUnselected = Color.White,
    chipSelected = Color(0xFFB6B6B6),
    dialog = Color.White,
    text = Color.Black,
    hint = Color(0xFF707070),
    tabs = Color(0xFFDADADA),
    infoChip = Color(0x80FFC3AD),
    onInfoChip = Color(0xFFAF3B17)
)

private val LocalColorScheme: ProvidableCompositionLocal<ColorScheme> = staticCompositionLocalOf { colorSchemeLight }

private val DarkColorScheme = darkColorScheme(
    background = app_bg_dark,
    onBackground = app_bg_light,
    primary = app_bg_dark,
    inverseOnSurface = lb_orange,   // Reserved for progress indicators.
    onSurface = Color.White,     // Text color (Which is ON surface/canvas)
)

private val LightColorScheme = lightColorScheme(
    background = app_bg_day,
    onBackground = app_bg_light,
    primary = app_bg_day,
    inverseOnSurface = lb_purple,   // Reserved for progress indicators.
    onSurface = Color.Black
)

@Immutable
data class Paddings(
    val defaultPadding: Dp = 16.dp,
    val tinyPadding: Dp = 4.dp,
    val smallPadding: Dp = 8.dp,
    val largePadding: Dp = 24.dp,

    // New set
    val horizontal: Dp = 9.dp,
    val vertical: Dp = 8.dp,
    val lazyListAdjacent: Dp = 6.dp,
    val coverArtAndTextGap: Dp = 8.dp,
    val insideCard: Dp = 16.dp,
    /** Padding for text inside custom made buttons.*/
    val insideButton: Dp = 8.dp,
    val adjacentDialogButtons: Dp = 8.dp,
    val chipsHorizontal: Dp = 6.dp,
    val insideDialog: Dp = 14.dp,
    val dialogContent: Dp = 8.dp
)
val LocalPaddings = staticCompositionLocalOf { Paddings() }

@Immutable
data class Sizes(
    val dropdownItem: Dp = 20.dp
)
val LocalSizes = staticCompositionLocalOf { Sizes() }

@Immutable
data class Shapes(
    // Change size field when changing this.
    val dialogs: Shape = RoundedCornerShape(4.dp),
    val chips: Shape = RoundedCornerShape(4.dp),
    val card: Shape = RoundedCornerShape(16.dp),
)

val LocalShapes = staticCompositionLocalOf { Shapes() }

@Immutable
data class TextStyles(
    val chips: TextStyle = TextStyle(fontWeight = FontWeight.Medium),
    val dropdownItem: TextStyle = TextStyle(fontWeight = FontWeight.Light, fontSize = 14.sp),
    // Dialog
    val dialogTitle: TextStyle = TextStyle(fontWeight = FontWeight.Light, fontSize = 16.sp),
    val dialogTitleBold: TextStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
    val dialogButtonText: TextStyle = TextStyle(fontWeight = FontWeight.Light, fontSize = 14.sp),
    val dialogText: TextStyle = TextStyle(fontWeight = FontWeight.Light, fontSize = 14.sp),
    val dialogTextField: TextStyle = TextStyle(fontWeight = FontWeight.Light, fontSize = 15.sp),
    val dialogTextBold: TextStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)
)

val LocalTextStyles = staticCompositionLocalOf { TextStyles() }

val LocalUiMode = staticCompositionLocalOf { Theme.FOLLOW_SYSTEM }

val LocalIsDarkTheme = staticCompositionLocalOf { false }

@Composable
fun StonksAppTheme(
    isSystemThemeDark: Boolean = isSystemInDarkTheme(),
    context: Context = LocalContext.current,
    appPreferences: AppPreferences = remember(context) { AppPreferencesImpl(context) },
    content: @Composable () -> Unit
) {
    val theme by appPreferences.theme.getFlow().collectAsState(initial = Theme.FOLLOW_SYSTEM)
    val isDarkTheme = remember(theme) {
        when (theme) {
            Theme.DARK -> true
            Theme.LIGHT -> false
            Theme.FOLLOW_SYSTEM -> isSystemThemeDark
        }
    }

    val localColorScheme = remember(theme) {
        when (theme) {
            Theme.LIGHT -> colorSchemeLight
            Theme.DARK -> colorSchemeDark
            Theme.FOLLOW_SYSTEM -> if (isSystemThemeDark) colorSchemeDark else colorSchemeLight
        }
    }

    val materialColorScheme = when (theme) {
        Theme.DARK -> DarkColorScheme
        Theme.LIGHT -> LightColorScheme
        Theme.FOLLOW_SYSTEM -> if (isSystemThemeDark) DarkColorScheme else LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val isLight = when (theme){
                Theme.DARK -> false
                Theme.LIGHT -> true
                Theme.FOLLOW_SYSTEM -> !isSystemThemeDark
            }
            WindowCompat.getInsetsController((view.context as Activity).window, view).run {
                isAppearanceLightStatusBars = isLight
                isAppearanceLightNavigationBars = isLight
            }
        }
    }

    CompositionLocalProvider(
        LocalPaddings provides Paddings(),
        LocalShapes provides Shapes(),
        LocalSizes provides Sizes(),
        LocalTextStyles provides TextStyles(),
        LocalColorScheme provides localColorScheme,
        LocalUiMode provides theme,
        LocalIsDarkTheme provides isDarkTheme,
        content = {
            MaterialTheme(
                colorScheme = materialColorScheme,
                content = content
            )
        }
    )
}

object StonksAppTheme {
    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val paddings: Paddings
        @Composable
        @ReadOnlyComposable
        get() = LocalPaddings.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val sizes: Sizes
        @Composable
        @ReadOnlyComposable
        get() = LocalSizes.current

    val textStyles: TextStyles
        @Composable
        @ReadOnlyComposable
        get() = LocalTextStyles.current
}