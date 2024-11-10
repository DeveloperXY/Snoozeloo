package com.developerxy.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developerxy.designsystem.ButtonSize
import com.developerxy.designsystem.SnoozelooButtonColors
import com.developerxy.designsystem.SnoozelooButtonDefaults

@Composable
fun SnoozelooOutlinedButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: ButtonSize = ButtonSize.MEDIUM,
    colors: SnoozelooButtonColors = SnoozelooButtonDefaults.colors(),
    text: String,
    onClick: () -> Unit
) {
    val outlinedColors = colors.copy(
        enabledBgColor = MaterialTheme.colorScheme.background,
        enabledTextColor = MaterialTheme.colorScheme.primary
    )
    val borderColor = outlinedColors.borderColor(enabled)
    val bgColor = outlinedColors.bgColor(enabled)
    val textColor = outlinedColors.textColor(enabled)
    val (height, fontSize, horizontalPadding) = size

    Box(
        modifier = modifier
            .requiredHeight(height)
            .background(bgColor, RoundedCornerShape(percent = 50))
            .clip(RoundedCornerShape(percent = 50))
            .indication(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = MaterialTheme.colorScheme.primary,
                    bounded = true,
                ),
            )
            .clickable(enabled = enabled) { onClick() }
            .border(
                width = 0.5.dp,
                color = borderColor,
                shape = RoundedCornerShape(percent = 50) // 16dp rounded corners
            )
            .padding(horizontal = horizontalPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = fontSize,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Preview
@Composable
fun SnoozelooOutlinedButtonPreview() {
    SnoozelooOutlinedButton(
        enabled = true,
        text = "Save",
        onClick = {}
    )
}

@Preview
@Composable
fun DisabledSnoozelooOutlinedButtonPreview() {
    SnoozelooOutlinedButton(
        enabled = false,
        text = "Save",
        onClick = {}
    )
}