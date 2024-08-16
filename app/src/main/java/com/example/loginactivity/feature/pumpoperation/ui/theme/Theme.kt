package com.example.loginactivity.feature.pumpoperation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.loginactivity.ui.theme.BackgroundPrimary
import com.example.loginactivity.ui.theme.ColorDutchWhite
import com.example.loginactivity.ui.theme.ColorError
import com.example.loginactivity.ui.theme.ColorOnPrimary
import com.example.loginactivity.ui.theme.ColorPrimary
import com.example.loginactivity.ui.theme.ColorPrimaryVariant
import com.example.loginactivity.ui.theme.SemiTransparentCardColor
import com.example.loginactivity.ui.theme.SurfaceColor


private val DarkColorScheme = darkColorScheme(
    primary = ColorPrimary,
    primaryContainer = ColorPrimaryVariant,
    onPrimary = ColorOnPrimary,
    background = BackgroundPrimary,
)

private val LightColorScheme = lightColorScheme(
    primary = ColorPrimary,
    primaryContainer = ColorPrimaryVariant,
    onPrimary = ColorOnPrimary,
    background = BackgroundPrimary,
    tertiaryContainer = SemiTransparentCardColor,
    onTertiary = ColorPrimaryVariant,
    tertiary = ColorDutchWhite,
    error = ColorError,
    errorContainer = ColorError,
    surfaceContainer = SurfaceColor
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
fun LoginActivityTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> LightColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}