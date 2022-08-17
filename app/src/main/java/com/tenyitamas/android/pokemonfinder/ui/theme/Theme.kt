package com.tenyitamas.android.pokemonfinder.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray

private val DarkColorPalette = darkColors(
    primary = TextWhite,
    primaryVariant = LightGray,
    secondary = Orange,
    background = DarkGray,
    onBackground = TextWhite,
    surface = MediumGray,
    onSurface = TextWhite,
    onPrimary = Color.White,
    onSecondary = Color.White,
)

private val LightColorPalette = lightColors(
    primary = Color.Black,
    primaryVariant = DarkGray,
    secondary = MediumBlue,
    background = Color.White,
    onBackground = DarkGray,
    surface = LightGray,
    onSurface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
)

@Composable
fun PokemonFinderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        DarkColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}