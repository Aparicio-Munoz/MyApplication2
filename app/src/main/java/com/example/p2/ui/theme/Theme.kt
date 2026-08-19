package com.example.p2.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Esquema de color para el tema oscuro (usado si dynamicColor no está disponible o desactivado)
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// Esquema de color para el tema claro (usado si dynamicColor no está disponible o desactivado)
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

/**
 * Tema principal de la app P2, basado en Material 3.
 *
 * @param darkTheme indica si se debe usar el esquema oscuro; por defecto sigue al sistema.
 * @param dynamicColor habilita "Dynamic Color" (Material You), disponible solo en Android 12+
 * (API 31, [Build.VERSION_CODES.S]). En versiones anteriores se usan los esquemas de color fijos
 * definidos arriba.
 * @param content contenido composable al que se le aplicará el tema.
 */
@Composable
fun P2Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // El color dinámico solo existe a partir de Android 12 (API 31)
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
