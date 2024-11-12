package com.developerxy.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.developerxy.designsystem.ButtonSize
import com.developerxy.designsystem.SnoozelooButtonColors
import com.developerxy.designsystem.SnoozelooButtonDefaults

@Composable
fun SnoozelooFilledButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: ButtonSize = ButtonSize.MEDIUM,
    colors: SnoozelooButtonColors = SnoozelooButtonDefaults.colors(),
    text: String,
    onClick: () -> Unit
) {
    val bgColor = colors.bgColor(enabled)
    val textColor = colors.textColor(enabled)
    val (height, fontSize, horizontalPadding) = size

    Box(
        modifier = modifier
            .requiredHeight(height)
            .background(bgColor, RoundedCornerShape(percent = 50))
            .clip(RoundedCornerShape(percent = 50))
            .clickable(
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = Color.White,
                    bounded = true,
                ),
            ) { onClick() }
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
fun SnoozelooFilledButtonPreview() {
    SnoozelooFilledButton(
        enabled = true,
        text = "Save",
        onClick = {}
    )
}

@Preview
@Composable
fun DisabledSnoozelooFilledButtonPreview() {
    SnoozelooFilledButton(
        enabled = false,
        text = "Save",
        onClick = {}
    )
}