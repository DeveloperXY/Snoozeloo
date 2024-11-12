package com.developerxy.alarmsettings

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developerxy.alarmsettings.ui.ActionBar
import com.developerxy.alarmsettings.ui.AlarmTimePicker

@Composable
fun AlarmSettingsScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    var closeEnabled by rememberSaveable { mutableStateOf(false) }
    var saveEnabled by rememberSaveable { mutableStateOf(true) }

    var hoursText by remember { mutableStateOf(TextFieldValue("")) }
    var minutesText by remember { mutableStateOf(TextFieldValue("")) }

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
                    saveEnabled = saveEnabled
                )
                Spacer(modifier = Modifier.height(24.dp))
                AlarmTimePicker(
                    hoursText = hoursText,
                    onHoursChanged = { hoursText = it },
                    minutesText = minutesText,
                    onMinutesChanged = { minutesText = it },
                    onShowKeyboard = { keyboardController?.show() },
                    onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun AlarmSettingsScreenPreview(modifier: Modifier = Modifier) {
    AlarmSettingsScreen()
}