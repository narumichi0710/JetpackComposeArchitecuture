package com.narumichi.presentation.layout.core.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

@Composable
fun CustomTheme(
    colors: CustomColorPalette = lightColorPalette(),
    typography: CustomTypography = CustomTypography(),
    children: @Composable() () -> Unit
) {
    CompositionLocalProvider(
        LocalSunshineColors provides colors,
        LocalSunshineTypography provides typography,
    ) {
        MaterialTheme(
            colors = colors.materialColors,
        ) {
            children()
        }
    }
}

object CustomTheme {
    val colors: CustomColorPalette
        @Composable
        @ReadOnlyComposable
        get() = LocalSunshineColors.current

    val typography: CustomTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalSunshineTypography.current
}

val Int.unitDp: Dp get() = Dp(value = this.times(8).toFloat())
val Double.unitDp: Dp get() = Dp(value = this.times(8).toFloat())

internal val LocalSunshineColors = staticCompositionLocalOf { lightColorPalette() }
internal val LocalSunshineTypography = staticCompositionLocalOf { CustomTypography() }