package com.developerxy.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developerxy.designsystem.SnoozelooButtonDefaults
import com.developerxy.designsystem.icon.SnoozelooIcons

@Composable
fun SnoozelooIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit = {}
) {
    val colors = SnoozelooButtonDefaults.colors()
    val bgColor = colors.bgColor(enabled)
    val iconTint = colors.iconTint(enabled)
    Box(
        modifier = modifier
            .requiredSize(32.dp)
            .background(bgColor, RoundedCornerShape(6.dp))
            .clickable(
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = Color.White, // Custom ripple color
                    bounded = true, // Ripple confined to the shape
                ),
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            tint = iconTint,
            contentDescription = contentDescription
        )
    }
}

@Preview
@Composable
fun SnoozelooIconButtonPreview() {
    SnoozelooIconButton(
        enabled = true,
        icon = SnoozelooIcons.ArrowBack,
        contentDescription = "Go back"
    )
}

@Preview
@Composable
fun DisabledSnoozelooIconButtonPreview() {
    SnoozelooIconButton(
        enabled = false,
        icon = SnoozelooIcons.Close,
        contentDescription = "Close"
    )
}