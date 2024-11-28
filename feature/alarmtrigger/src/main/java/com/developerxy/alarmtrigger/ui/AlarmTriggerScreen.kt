package com.developerxy.alarmtrigger.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developerxy.alarmtrigger.ui.model.Alarm
import com.developerxy.designsystem.ButtonSize
import com.developerxy.designsystem.component.SnoozelooFilledButton
import com.developerxy.designsystem.component.SnoozelooOutlinedButton
import com.developerxy.designsystem.icon.SnoozelooIcons
import com.developerxy.formatAsDisplayTime

@Composable
fun AlarmTriggerScreen(
    modifier: Modifier = Modifier,
    alarm: Alarm
) {
    Scaffold(modifier = modifier) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(color = MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(horizontal = 44.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.requiredSize(50.dp),
                    imageVector = SnoozelooIcons.Alarm,
                    contentDescription = "Alarm",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(36.dp))
                Text(
                    formatAsDisplayTime(alarm.time.hours, alarm.time.minutes),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 82.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    alarm.name,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Spacer(Modifier.height(16.dp))
                SnoozelooFilledButton(
                    modifier = Modifier.fillMaxWidth(),
                    size = ButtonSize.LARGE,
                    text = "Turn off"
                ) { }
                Spacer(Modifier.height(10.dp))
                SnoozelooOutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    size = ButtonSize.LARGE,
                    text = "Snooze for 5 min"
                ) { }
            }
        }
    }
}

@Preview
@Composable
fun AlarmTriggerScreenPreview(modifier: Modifier = Modifier) {
//    AlarmTriggerScreen()
}