package com.developerxy.alarmsettings.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developerxy.designsystem.component.SnoozelooSurface
import com.developerxy.designsystem.component.TimeInputField

@Composable
fun AlarmTimePicker(
    modifier: Modifier = Modifier,
    hours: Int? = null,
    onHoursChanged: (Int?) -> Unit,
    minutes: Int? = null,
    onMinutesChanged: (Int?) -> Unit,
    alarmTimePreviewText: String? = null,
    onDone: () -> Unit,
    onShowKeyboard: () -> Unit
) {
    val hoursFocusRequester = remember { FocusRequester() }
    val minutesFocusRequester = remember { FocusRequester() }
    var areHoursFocused by remember { mutableStateOf(false) }
    var areMinutesFocused by remember { mutableStateOf(false) }

    SnoozelooSurface(modifier = modifier) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AlarmTimeInputField(
                    modifier = Modifier.weight(1f),
                    isFocused = areHoursFocused,
                    value = hours,
                    focusRequester = hoursFocusRequester,
                    onValueChanged = onHoursChanged,
                    onValidateInput = { text -> text.shouldBeInHoursRange() },
                    onFocusChanged = { isFocused ->
                        if (isFocused && !areHoursFocused) {
                            onShowKeyboard()
                        }
                        areHoursFocused = isFocused
                    },
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(
                        onNext = { minutesFocusRequester.requestFocus() }
                    ),
                )

                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = ":",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 36.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )

                AlarmTimeInputField(
                    modifier = Modifier.weight(1f),
                    isFocused = areMinutesFocused,
                    value = minutes,
                    focusRequester = minutesFocusRequester,
                    onValueChanged = onMinutesChanged,
                    onValidateInput = { text -> text.shouldBeInMinutesRange() },
                    onFocusChanged = { isFocused ->
                        if (isFocused && !areMinutesFocused) {
                            onShowKeyboard()
                        }
                        areMinutesFocused = isFocused
                    },
                    imeAction = ImeAction.Done,
                    keyboardActions = KeyboardActions(
                        onDone = { onDone() }
                    ),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedVisibility(alarmTimePreviewText != null) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = alarmTimePreviewText.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

@Composable
private fun AlarmTimeInputField(
    modifier: Modifier = Modifier,
    value: Int?,
    hint: String = "00",
    isFocused: Boolean = false,
    onValueChanged: (Int?) -> Unit,
    focusRequester: FocusRequester? = null,
    onFocusChanged: (Boolean) -> Unit = {},
    imeAction: ImeAction,
    keyboardActions: KeyboardActions,
    onValidateInput: (String) -> Boolean = { true },
) {
    SnoozelooSurface(
        modifier = modifier,
        backgroundColor = MaterialTheme.colorScheme.surface
    ) {
        TimeInputField(
            hint = hint,
            isFocused = isFocused,
            value = value,
            focusRequester = focusRequester,
            onValueChanged = onValueChanged,
            onValidateInput = onValidateInput,
            onFocusChanged = onFocusChanged,
            imeAction = imeAction,
            keyboardActions = keyboardActions,
        )
    }
}

private fun String.shouldBeInHoursRange() = shouldBeInRange(0..23)
private fun String.shouldBeInMinutesRange() = shouldBeInRange(0..59)

private fun String.shouldBeInRange(range: IntRange): Boolean {
    val number = toIntOrNull()
    return number == null || (number in range)
}