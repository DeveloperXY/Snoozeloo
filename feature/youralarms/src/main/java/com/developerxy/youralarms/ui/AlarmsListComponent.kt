package com.developerxy.youralarms.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.developerxy.designsystem.icon.SnoozelooIcons
import com.developerxy.youralarms.ui.model.Alarm
import kotlinx.coroutines.launch

@Composable
fun AlarmsList(
    modifier: Modifier = Modifier,
    alarms: List<Alarm>,
    onDeleteAlarm: (alarm: Alarm) -> Unit = {},
    onToggleAlarmActiveState: (alarm: Alarm) -> Unit = {}
) {
    val currentOnDeleteAlarm by rememberUpdatedState(onDeleteAlarm)
    val currentOnToggleAlarmActiveState by rememberUpdatedState(onToggleAlarmActiveState)

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(alarms.size, key = { alarms[it].id }) {
            SwipeToDelete(
                modifier = Modifier.padding(horizontal = 16.dp),
                onDelete = { currentOnDeleteAlarm(alarms[it]) }
            ) {
                Alarm(
                    alarm = alarms[it],
                    onToggleAlarmActiveState = {
                        currentOnToggleAlarmActiveState(alarms[it])
                    }
                )
            }
        }
    }
}

@Composable
private fun Swipeable(
    modifier: Modifier = Modifier,
    offsetX: Animatable<Float, AnimationVector1D>,
    onDragEnd: () -> Unit,
    onOffsetXShouldSnapTo: (Float) -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier
            .fillMaxWidth()
            .offset(x = offsetX.value.asPxToDp())
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = onDragEnd
                ) { change, dragAmount ->
                    change.consume()
                    onOffsetXShouldSnapTo(offsetX.value + dragAmount)
                }
            }
    ) {
        content()
    }
}

@Composable
private fun SwipeToDelete(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
    content: @Composable () -> Unit
) {
    var isDeleted by remember { mutableStateOf(false) }
    var showBgDeleteIcons by remember { mutableStateOf(true) }
    val offsetX = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()
    val currentOnDelete by rememberUpdatedState(onDelete)

    if (!isDeleted) {
        Box(
            modifier
                .fillMaxWidth()
                .animateItemRemoval(isDeleted)
        ) {
            if (showBgDeleteIcons) {
                Icon(
                    modifier = Modifier
                        .requiredSize(48.dp)
                        .align(Alignment.CenterEnd),
                    imageVector = SnoozelooIcons.Bin,
                    contentDescription = "Delete alarm",
                    tint = MaterialTheme.colorScheme.primary
                )
                Icon(
                    modifier = Modifier
                        .requiredSize(48.dp)
                        .align(Alignment.CenterStart),
                    imageVector = SnoozelooIcons.Bin,
                    contentDescription = "Delete alarm",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Swipeable(
                offsetX = offsetX,
                onDragEnd = {
                    coroutineScope.launch {
                        if (offsetX.value > 150 || offsetX.value < -150) {
                            showBgDeleteIcons = false
                            offsetX.animateTo(
                                targetValue = if (offsetX.value > 0) 1000f else -1000f,
                                animationSpec = tween(durationMillis = 300)
                            )
                            isDeleted = true
                            currentOnDelete()
                        } else {
                            offsetX.animateTo(
                                0f,
                                animationSpec = tween(durationMillis = 300)
                            )
                        }
                    }
                },
                onOffsetXShouldSnapTo = { newValue ->
                    coroutineScope.launch {
                        offsetX.snapTo(newValue)
                    }
                }
            ) {
                content()
            }
        }
    }
}

@Composable
private fun Float.asPxToDp(): Dp {
    val density = LocalDensity.current
    return with(density) { toDp() }
}

@Composable
private fun Modifier.animateItemRemoval(isDeleted: Boolean): Modifier {
    return if (isDeleted) {
        this.height(animateDpAsState(targetValue = 0.dp, animationSpec = tween(300)).value)
    } else {
        this
    }
}

@Preview
@Composable
fun AlarmsListPreview() {
    AlarmsList(alarms = mockAlarms)
}