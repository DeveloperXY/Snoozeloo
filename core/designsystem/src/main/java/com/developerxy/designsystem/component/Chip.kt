package com.developerxy.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SnoozelooChip(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    text: String,
    onClick: () -> Unit
) {
    val colors = SnoozelooChipDefaults.colors()
    val chipBgColor = colors.bgColor(selected)
    val chipTextColor = colors.textColor(selected)

    Box(
        modifier = modifier
            .wrapContentSize()
            .background(chipBgColor, RoundedCornerShape(50))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = Color.White, // Custom ripple color
                    bounded = true, // Ripple confined to the shape
                ),
            ) { onClick() }
            .padding(vertical = 10.dp, horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text, color = chipTextColor, style = MaterialTheme.typography.labelMedium)
    }
}

@Immutable
data class SnoozelooChipColors(
    val checkedBgColor: Color,
    val uncheckedBgColor: Color,
    val checkedTextColor: Color,
    val uncheckedTextColor: Color,
) {
    @Stable
    fun bgColor(checked: Boolean): Color =
        if (checked) checkedBgColor else uncheckedBgColor

    @Stable
    fun textColor(checked: Boolean): Color =
        if (checked) checkedTextColor else uncheckedTextColor
}

object SnoozelooChipDefaults {
    @Composable
    fun colors(): SnoozelooChipColors {
        return SnoozelooChipColors(
            checkedBgColor = MaterialTheme.colorScheme.primary,
            uncheckedBgColor = MaterialTheme.colorScheme.background,
            checkedTextColor = Color.White,
            uncheckedTextColor = Color.Black
        )
    }
}

@Preview
@Composable
fun SnoozelooChipPreview() {
    SnoozelooChip(
        selected = true,
        text = "Tu",
        onClick = {}
    )
}

@Preview
@Composable
fun UncheckedSnoozelooChipPreview() {
    SnoozelooChip(
        selected = false,
        text = "We",
        onClick = {}
    )
}