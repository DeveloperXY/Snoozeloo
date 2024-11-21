package com.developerxy.youralarms.ui

import androidx.compose.animation.AnimatedVisibility
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
import com.developerxy.ui.amOrPm
import com.developerxy.ui.formatAsDisplayTime
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

                    AnimatedVisibility(alarm.isActive && alarm.timeUntilNextOccurrence != null) {
                        Column {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = alarm.timeUntilNextOccurrence.orEmpty(),
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = SnoozelooTextGray,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp
                                )
                            )
                        }
                    }
                }

                SnoozelooSwitch(
                    checked = alarm.isActive,
                    onCheckedChange = {
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
                val selectedDays = alarm.selectedDays

                labels.forEachIndexed { index, label ->
                    SnoozelooChip(
                        text = label,
                        selected = selectedDays.contains(index),
                        onClick = {}
                    )
                }
            }

            AnimatedVisibility(alarm.isActive && alarm.timeRequiredForXHoursOfSleep != null) {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        alarm.timeRequiredForXHoursOfSleep.orEmpty(),
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = SnoozelooTextGray, fontWeight = FontWeight.Medium, fontSize = 14.sp
                        )
                    )
                }
            }
        }
    }
}

private fun AlarmInfo.displayTime(): String = formatAsDisplayTime(time.hours, time.minutes)

private fun AlarmInfo.amOrPm(): String = amOrPm(time.hours)

@Preview
@Composable
fun AlarmPreview() {
    Alarm(alarm = mockAlarms[0])
}