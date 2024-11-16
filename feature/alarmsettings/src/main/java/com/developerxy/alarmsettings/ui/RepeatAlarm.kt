package com.developerxy.alarmsettings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.developerxy.alarmsettings.R
import com.developerxy.designsystem.component.SnoozelooChip
import com.developerxy.designsystem.component.SnoozelooSurface

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RepeatAlarm(
    modifier: Modifier = Modifier,
    selectedDays: IntArray = intArrayOf(),
    onDaySelected: (day: Int) -> Unit,
    onDayUnselected: (day: Int) -> Unit,
) {
    SnoozelooSurface(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.repeat),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                val daysOfTheWeek = arrayOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")
                daysOfTheWeek.forEachIndexed { index, dayOfTheWeek ->
                    val isDaySelected = selectedDays.contains(index)
                    SnoozelooChip(
                        selected = isDaySelected,
                        text = dayOfTheWeek,
                        onClick = {
                            if (isDaySelected) onDayUnselected(index) else onDaySelected(index)
                        }
                    )
                }
            }
        }
    }
}