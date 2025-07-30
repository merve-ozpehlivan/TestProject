package com.example.inventivtestcase.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColors(
    primary = Purple80,
    secondary = PurpleGrey80,
)

private val LightColorScheme = lightColors(
    primary = Purple40,
    secondary = PurpleGrey40,


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun InventivTestCaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colors = lightColors(
            primary = Yesil,
            primaryVariant = AcikYesil,
            onPrimary = Beyaz,
            surface = Beyaz,
            background = Gri,
            onBackground = Siyah,
            onSurface = Siyah
        ),
        typography = Typography,
        shapes = Shapes(),
        content = content
    )

}