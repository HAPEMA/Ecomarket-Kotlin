package com.example.ecomarket.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


private val LightColorScheme = lightColorScheme(
    primary = Cafe,
    onPrimary = Color.White,
    primaryContainer = CafeOscuro,
    onPrimaryContainer = Color.White,

    secondary = Rosado,
    onSecondary = CafeOscuro,
    secondaryContainer = Rosado,
    onSecondaryContainer = CafeOscuro,

    tertiary = ChipPink,
    onTertiary = Cafe,

    background = Crema,
    onBackground = GrisTexto,
    surface = Color.White,
    onSurface = GrisTexto,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = GrisTexto,

    outline = OutlineSoft
)

private val DarkColorScheme = darkColorScheme(
    primary = Cafe,
    onPrimary = Color.White,
    primaryContainer = CafeOscuro,
    onPrimaryContainer = Color.White,

    secondary = Rosado,             // conserva identidad, contrasta bien sobre fondo oscuro
    onSecondary = CafeOscuro,

    tertiary = ChipPink,
    onTertiary = CafeOscuro,

    background = FondoDark,
    onBackground = GrisClaroTexto,
    surface = Color(0xFF241F1B),    // un poco más claro que background
    onSurface = GrisClaroTexto,
    surfaceVariant = Color(0xFF2C2621),
    onSurfaceVariant = GrisClaroTexto,

    outline = Color(0xFF73665B)
)

@Composable
fun EcoMarketTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // <-- marca primero
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // tu set de tipografías
        content = content
    )
}
