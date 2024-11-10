package com.developerxy.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Immutable
data class SnoozelooButtonColors(
    val enabledBgColor: Color,
    val disabledBgColor: Color,
    val enabledTextColor: Color,
    val disabledTextColor: Color,
    val enabledBorderColor: Color,
    val disabledBorderColor: Color,
    val enabledIconTint: Color,
    val disabledIconTint: Color,
) {
    @Stable
    fun bgColor(enabled: Boolean): Color =
        if (enabled) enabledBgColor else disabledBgColor

    @Stable
    fun textColor(enabled: Boolean): Color =
        if (enabled) enabledTextColor else disabledTextColor

    @Stable
    fun borderColor(enabled: Boolean): Color =
        if (enabled) enabledBorderColor else disabledBorderColor

    @Stable
    fun iconTint(enabled: Boolean): Color =
        if (enabled) enabledIconTint else disabledIconTint
}

object SnoozelooButtonDefaults {
    @Composable
    fun colors(): SnoozelooButtonColors {
        return SnoozelooButtonColors(
            enabledBgColor = MaterialTheme.colorScheme.primary,
            disabledBgColor = MaterialTheme.colorScheme.outline,
            enabledTextColor = Color.White,
            disabledTextColor = Color.White,
            enabledBorderColor = MaterialTheme.colorScheme.primary,
            disabledBorderColor = Color.Unspecified,
            enabledIconTint = Color.White,
            disabledIconTint = Color.White
        )
    }
}