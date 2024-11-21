package com.developerxy.youralarms.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developerxy.designsystem.component.SnoozelooChip
import com.developerxy.designsystem.component.SnoozelooSurface
import com.developerxy.designsystem.component.SnoozelooSwitch
import com.developerxy.designsystem.theme.SnoozelooTextGray
import com.developerxy.youralarms.ui.model.Alarm as AlarmInfo

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Alarm(
    modifier: Modifier = Modifier,
    alarm: AlarmInfo,
    onToggleAlarmActiveState: () -> Unit = {}
) {
    println(alarm)
    val currentOnToggleAlarmActiveState by rememberUpdatedState(onToggleAlarmActiveState)

    SnoozelooSurface(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    if (alarm.name.isNotEmpty()) {
                        Text(
                            alarm.name,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            alarm.displayTime(),
                            modifier = Modifier.alignByBaseline(),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 42.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            alarm.amOrPm(),
                            modifier = Modifier.alignByBaseline(),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 24.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    }

                    if (alarm.isActive) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Alarm in 30min", style = MaterialTheme.typography.labelMedium.copy(
                                color = SnoozelooTextGray,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        )
                    }
                }

                SnoozelooSwitch(
                    checked = alarm.isActive,
                    onCheckedChange = {
                        println(alarm)
                        currentOnToggleAlarmActiveState()
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                val labels = listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")
                val selectedDays = alarm.getSelectedDays()

                labels.forEachIndexed { index, label ->
                    SnoozelooChip(
                        text = label,
                        selected = selectedDays.contains(index),
                        onClick = {}
                    )
                }
            }

            if (alarm.isActive) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Go to bed at 02:00AM to get 8h of sleep",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = SnoozelooTextGray, fontWeight = FontWeight.Medium, fontSize = 14.sp
                    )
                )
            }
        }
    }
}

private fun AlarmInfo.displayTime(): String {
    return with(time) {
        "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}"
    }
}

private fun AlarmInfo.amOrPm(): String {
    return with(time) {
        if (hours < 12) "AM" else "PM"
    }
}

fun AlarmInfo.getSelectedDays(): List<Int> {
    return (0 until 8)
        .filter { (selectedDays.toInt() shr it) and 1 == 1 }
}

@Preview
@Composable
fun AlarmPreview() {
    Alarm(alarm = mockAlarms[0])
}