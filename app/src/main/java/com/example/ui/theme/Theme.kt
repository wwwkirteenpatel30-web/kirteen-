package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = GeoMint,
    secondary = GeoAccent,
    tertiary = GeoSecondary,
    background = DarkBg,
    surface = DarkSurface,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color(0xFFF1F8F5),
    onSurface = Color(0xFFF1F8F5),
    outline = GeoSecondary
)

private val LightColorScheme = lightColorScheme(
    primary = GeoSecondary,       // 0xFF2D6A4F
    secondary = GeoPrimary,       // 0xFF1B4332
    tertiary = GeoAccent,         // 0xFF40916C
    background = LightBg,         // 0xFFF1F8F5
    surface = LightSurface,       // 0xFFFFFFFF
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = GeoPrimary,
    onSurface = GeoPrimary,
    outline = GeoPale             // 0xFFD8F3DC
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep it false to preserve Geometric Balance brand colours!
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
