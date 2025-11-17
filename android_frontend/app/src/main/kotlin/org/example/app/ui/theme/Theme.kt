package org.example.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Ocean Professional palette
val BluePrimary = Color(0xFF2563EB)
val AmberSecondary = Color(0xFFF59E0B)
val ErrorRed = Color(0xFFEF4444)
val Background = Color(0xFFF9FAFB)
val Surface = Color(0xFFFFFFFF)
val TextPrimary = Color(0xFF111827)

private val LightColors: ColorScheme = lightColorScheme(
    primary = BluePrimary,
    onPrimary = Color.White,
    secondary = AmberSecondary,
    onSecondary = Color.Black,
    error = ErrorRed,
    onError = Color.White,
    background = Background,
    onBackground = TextPrimary,
    surface = Surface,
    onSurface = TextPrimary,
)

private val DarkColors: ColorScheme = darkColorScheme(
    primary = BluePrimary,
    onPrimary = Color.White,
    secondary = AmberSecondary,
    onSecondary = Color.Black,
    error = ErrorRed,
    onError = Color.White,
    background = Color(0xFF0B0F14),
    onBackground = Color(0xFFE5E7EB),
    surface = Color(0xFF111827),
    onSurface = Color(0xFFE5E7EB),
)

val Shapes = androidx.compose.material3.Shapes(
    extraSmall = RoundedCornerShape(6),
    small = RoundedCornerShape(10),
    medium = RoundedCornerShape(14),
    large = RoundedCornerShape(18),
    extraLarge = RoundedCornerShape(22),
)

val AppTypography = Typography()

/**
 * PUBLIC_INTERFACE
 * OceanProfessionalTheme
 *
 * Wraps MaterialTheme with the Ocean Professional color scheme and modern shapes.
 */
@Composable
fun OceanProfessionalTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        shapes = Shapes,
        content = content
    )
}
