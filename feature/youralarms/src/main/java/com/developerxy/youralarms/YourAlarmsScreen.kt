package com.developerxy.youralarms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.developerxy.designsystem.component.SnoozelooFab
import com.developerxy.designsystem.icon.SnoozelooIcons
import com.developerxy.youralarms.ui.AlarmsList
import com.developerxy.youralarms.ui.mockAlarms
import com.developerxy.youralarms.ui.model.Alarm
import org.koin.androidx.compose.koinViewModel

@Composable
fun YourAlarmsScreen(
    modifier: Modifier = Modifier,
    viewModel: YourAlarmsViewModel = koinViewModel(),
    onCreateNewAlarm: () -> Unit,
) {
    val alarms by viewModel.alarms.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadAlarms()
    }

    YourAlarmsScreenContent(
        modifier = modifier,
        alarms = alarms,
        onCreateNewAlarm = onCreateNewAlarm,
        onDeleteAlarm = viewModel::deleteAlarm,
        onToggleAlarmActiveState = viewModel::toggleAlarmActiveState,
    )
}

@Composable
fun YourAlarmsScreenContent(
    modifier: Modifier = Modifier,
    alarms: List<Alarm>,
    onCreateNewAlarm: () -> Unit = {},
    onDeleteAlarm: (Alarm) -> Unit = {},
    onToggleAlarmActiveState: (Alarm) -> Unit = {},
) {
    Scaffold { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    color = MaterialTheme.colorScheme.surface
                )
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                    ),
                    text = stringResource(R.string.your_alarms),
                    style = MaterialTheme.typography.titleMedium
                        .copy(
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                )
                Spacer(modifier = Modifier.height(24.dp))
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    androidx.compose.animation.AnimatedVisibility(alarms.isEmpty()) {
                        NoAlarmsView()
                    }
                    androidx.compose.animation.AnimatedVisibility(alarms.isNotEmpty()) {
                        AlarmsList(
                            alarms = alarms,
                            onToggleAlarmActiveState = onToggleAlarmActiveState,
                            onDeleteAlarm = onDeleteAlarm
                        )
                    }
                }
            }

            SnoozelooFab(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp),
                icon = SnoozelooIcons.Add,
                onClick = onCreateNewAlarm
            )
        }
    }
}

@Composable
private fun NoAlarmsView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = SnoozelooIcons.Alarm,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )
        Text(
            stringResource(R.string.no_alarms_set_description),
            modifier = Modifier.padding(top = 32.dp),
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Preview(name = "No alarms")
@Composable
fun YourAlarmsScreenEmptyContentPreview(modifier: Modifier = Modifier) {
    YourAlarmsScreenContent(
        alarms = listOf()
    )
}

@Preview(name = "View of set alarms")
@Composable
fun YourAlarmsScreenContentPreview(modifier: Modifier = Modifier) {
    YourAlarmsScreenContent(alarms = mockAlarms)
}