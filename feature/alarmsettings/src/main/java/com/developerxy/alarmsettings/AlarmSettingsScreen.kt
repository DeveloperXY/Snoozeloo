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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.developerxy.ui.RingtonesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapLatest
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun AlarmSettingsScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    onShowRingtonesList: () -> Unit = {},
    alarmSettingsViewModel: AlarmSettingsViewModel = koinViewModel(),
    ringtonesViewModel: RingtonesViewModel = koinViewModel()
) {
    val hours by alarmSettingsViewModel.hours.collectAsState()
    val volume by alarmSettingsViewModel.volume.collectAsState()
    val minutes by alarmSettingsViewModel.minutes.collectAsState()
    val alarmName by alarmSettingsViewModel.alarmName.collectAsState()
    val selectedDays by alarmSettingsViewModel.selectedDays.collectAsState()
    val vibrate by alarmSettingsViewModel.shouldAlarmVibrate.collectAsState()
    val saveEnabled by alarmSettingsViewModel.isAlarmInfoValid.collectAsState()
    val alarmTimePreviewText by alarmSettingsViewModel.alarmTimePreviewText.collectAsState()
    val selectedRingtone by ringtonesViewModel.selectedRingtone.collectAsState()

    val scrollState = rememberScrollState()
    var showAlarmNameDialog by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager: FocusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        ringtonesViewModel.selectedRingtone
            .mapLatest { selectedRingtone ->
                alarmSettingsViewModel.setSelectedRingtone(selectedRingtone)
            }.collect()
    }

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
                    saveEnabled = saveEnabled,
                    onClose = navigateBack,
                    onSave = {
                        alarmSettingsViewModel.saveNewAlarm(
                            onComplete = navigateBack
                        )
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))

                AlarmTimePicker(
                    hours = hours,
                    onHoursChanged = alarmSettingsViewModel::setHours,
                    minutes = minutes,
                    onMinutesChanged = alarmSettingsViewModel::setMinutes,
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
                    onDaySelected = alarmSettingsViewModel::selectDay,
                    onDayUnselected = alarmSettingsViewModel::unselectDay
                )
                Spacer(modifier = Modifier.height(16.dp))
                AlarmRingtone(
                    modifier = Modifier.clickable {
                        onShowRingtonesList()
                    },
                    text = selectedRingtone.title
                )
                Spacer(modifier = Modifier.height(16.dp))
                AlarmVolume(
                    volume = volume,
                    onVolumeChanged = alarmSettingsViewModel::setVolume
                )
                Spacer(modifier = Modifier.height(16.dp))
                Vibrate(
                    enabled = vibrate,
                    onToggleVibrate = alarmSettingsViewModel::toggleShouldAlarmVibrate
                )
            }

            if (showAlarmNameDialog) {
                AlarmNameDialog(
                    initialAlarmName = alarmName,
                    onAlarmNameChanged = alarmSettingsViewModel::setAlarmName,
                    onDismiss = { showAlarmNameDialog = false }
                )
            }
        }
    }
}

@Preview
@Composable
fun AlarmSettingsScreenPreview(modifier: Modifier = Modifier) {
    AlarmSettingsScreen()
}