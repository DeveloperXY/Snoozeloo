package com.developerxy.alarmsettings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developerxy.alarmsettings.ui.ActionBar
import com.developerxy.alarmsettings.ui.AlarmName
import com.developerxy.alarmsettings.ui.AlarmRingtone
import com.developerxy.alarmsettings.ui.AlarmTimePicker
import com.developerxy.alarmsettings.ui.AlarmVolume
import com.developerxy.alarmsettings.ui.RepeatAlarm
import com.developerxy.alarmsettings.ui.Vibrate
import com.developerxy.alarmsettings.ui.dialog.AlarmNameDialog
import java.util.Calendar
import java.util.concurrent.TimeUnit

@Composable
fun AlarmSettingsScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    var closeEnabled by rememberSaveable { mutableStateOf(true) }
    var saveEnabled by rememberSaveable { mutableStateOf(false) }

    var hours by remember { mutableStateOf<Int?>(null) }
    var minutes by remember { mutableStateOf<Int?>(null) }
    var selectedDays by remember { mutableStateOf<List<Int>>(listOf()) }

    var volume by remember { mutableStateOf(0) }
    var vibrate by rememberSaveable { mutableStateOf(true) }

    var showAlarmNameDialog by remember { mutableStateOf(false) }
    var alarmName by remember { mutableStateOf("My alarm") }
    var alarmTimePreviewText by remember { mutableStateOf<String?>(null) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager: FocusManager = LocalFocusManager.current

    Scaffold(modifier = modifier) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                ActionBar(
                    closeEnabled = closeEnabled,
                    saveEnabled = saveEnabled,
                    onClose = navigateBack
                )
                Spacer(modifier = Modifier.height(24.dp))

                LaunchedEffect(hours, minutes) {
                    val isAlarmTimeValid = hours != null && minutes != null
                    alarmTimePreviewText = if (isAlarmTimeValid) {
                        getRelativeAlarmTime(hours!!, minutes!!)
                    } else {
                        null
                    }

                    saveEnabled = isAlarmTimeValid
                }
                AlarmTimePicker(
                    hours = hours,
                    onHoursChanged = { hours = it },
                    minutes = minutes,
                    onMinutesChanged = { minutes = it },
                    alarmTimePreviewText = alarmTimePreviewText,
                    onShowKeyboard = { keyboardController?.show() },
                    onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
                AlarmName(
                    modifier = Modifier.clickable {
                        showAlarmNameDialog = true
                    },
                    text = alarmName
                )
                Spacer(modifier = Modifier.height(16.dp))
                RepeatAlarm(
                    selectedDays = selectedDays.toIntArray(),
                    onDaySelected = {
                        selectedDays = selectedDays + it
                    },
                    onDayUnselected = {
                        selectedDays = selectedDays - it
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                AlarmRingtone(text = "Default")
                Spacer(modifier = Modifier.height(16.dp))
                AlarmVolume(
                    volume = volume,
                    onVolumeChanged = {
                        volume = it
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Vibrate(
                    enabled = vibrate,
                    onToggleVibrate = {
                        vibrate = !vibrate
                    }
                )
            }

            if (showAlarmNameDialog) {
                AlarmNameDialog(
                    initialAlarmName = alarmName,
                    onAlarmNameChanged = { alarmName = it },
                    onDismiss = { showAlarmNameDialog = false }
                )
            }
        }
    }
}

private fun getRelativeAlarmTime(hours: Int, minutes: Int): String {
    val now = Calendar.getInstance()
    val currentHour = now.get(Calendar.HOUR_OF_DAY)
    val currentMinute = now.get(Calendar.MINUTE)

    val alarmTimeInMinutes = hours * 60 + minutes
    val currentTimeInMinutes = currentHour * 60 + currentMinute

    val differenceInMinutes = if (alarmTimeInMinutes >= currentTimeInMinutes) {
        alarmTimeInMinutes - currentTimeInMinutes
    } else {
        // If the alarm time is for the next day
        (24 * 60) - currentTimeInMinutes + alarmTimeInMinutes
    }

    val diffHours = TimeUnit.MINUTES.toHours(differenceInMinutes.toLong()).toInt()
    val diffMinutes = (differenceInMinutes % 60)

    return buildString {
        append("Alarm in ")
        if (diffHours > 0) append("${diffHours}h ")
        if (diffMinutes > 0) append("${diffMinutes}min")
    }.trim()
}

@Preview
@Composable
fun AlarmSettingsScreenPreview(modifier: Modifier = Modifier) {
    AlarmSettingsScreen()
}