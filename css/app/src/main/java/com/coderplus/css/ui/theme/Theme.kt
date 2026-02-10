package com.coderplus.css.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = ColorPrimary,
    secondary = ColorSecondary,
    tertiary = ColorAccent,
    background = ColorBackground,
    surface = ColorSurface,
    onPrimary = ColorSurface,
    onSecondary = ColorSurface,
    onTertiary = ColorSurface,
    onBackground = ColorText,
    onSurface = ColorText,
    surfaceVariant = ColorSurfaceMuted,
    onSurfaceVariant = ColorTextMuted,
    outline = ColorBorder
)

private val DarkColorScheme = darkColorScheme(
    primary = ColorPrimaryDark,
    secondary = ColorSecondaryDark,
    tertiary = ColorAccent,
    background = ColorBackgroundDark,
    surface = ColorSurfaceDark,
    onPrimary = ColorText,
    onSecondary = ColorTextDark,
    onTertiary = ColorTextDark,
    onBackground = ColorTextDark,
    onSurface = ColorTextDark,
    surfaceVariant = ColorSurfaceDark,
    onSurfaceVariant = ColorTextMuted
)

@Composable
fun CssUcaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
