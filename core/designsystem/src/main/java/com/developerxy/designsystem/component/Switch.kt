package com.developerxy.designsystem.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun SnoozelooSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    size: SwitchSize = SwitchSize.MEDIUM,
    onCheckedChange: () -> Unit = {},
) {
    SnoozelooSwitchImpl(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        size = size
    )
}

@Composable
private fun SnoozelooSwitchImpl(
    modifier: Modifier = Modifier,
    checked: Boolean,
    colors: SnoozelooSwitchColors = SnoozelooSwitchDefaults.colors(),
    size: SwitchSize,
    onCheckedChange: () -> Unit = {},
) {
    val outline = 2.dp
    val (trackWidth, trackHeight, thumbSize) = size
    val thumbOffset by animateDpAsState(
        if (checked) (trackWidth - thumbSize).dp - outline else 0.dp + outline
    )

    val trackColor = colors.trackColor(checked = checked)
    val thumbColor = colors.thumbColor(checked = checked)

    val newCallback = rememberUpdatedState(onCheckedChange)

    Box(
        modifier = Modifier
            .size(width = trackWidth.dp, height = trackHeight.dp)
            .background(trackColor, RoundedCornerShape(50))
            .pointerInput(Unit) {
                detectTapGestures(onTap = { onCheckedChange() })
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Spacer(modifier = Modifier.size(2.dp))
        Box(
            Modifier
                .size(thumbSize.dp)
                .offset(x = thumbOffset, y = 0.dp)
                .background(thumbColor, CircleShape)
        )
        Spacer(modifier = Modifier.size(2.dp))
    }
}

enum class SwitchSize(val trackWidth: Int, val trackHeight: Int, val thumbSize: Int) {
    SMALL(trackWidth = 41, trackHeight = 24, thumbSize = 20),
    MEDIUM(trackWidth = 51, trackHeight = 30, thumbSize = 26);

    operator fun component1() = trackWidth
    operator fun component2() = trackHeight
    operator fun component3() = thumbSize
}

@Immutable
data class SnoozelooSwitchColors(
    val checkedThumbColor: Color,
    val checkedTrackColor: Color,
    val uncheckedThumbColor: Color,
    val uncheckedTrackColor: Color,
) {
    @Stable
    fun thumbColor(checked: Boolean): Color =
        if (checked) checkedThumbColor else uncheckedThumbColor

    @Stable
    fun trackColor(checked: Boolean): Color =
        if (checked) checkedTrackColor else uncheckedTrackColor
}

object SnoozelooSwitchDefaults {
    @Composable
    fun colors(): SnoozelooSwitchColors {
        return SnoozelooSwitchColors(
            checkedThumbColor = Color.White,
            uncheckedThumbColor = Color.White,
            checkedTrackColor = MaterialTheme.colorScheme.primary,
            uncheckedTrackColor = MaterialTheme.colorScheme.primaryContainer,
        )
    }
}