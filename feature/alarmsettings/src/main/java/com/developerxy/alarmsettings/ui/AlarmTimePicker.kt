package com.developerxy.alarmsettings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developerxy.designsystem.component.SnoozelooSurface
import com.developerxy.designsystem.component.TimeInputField

@Composable
fun AlarmTimePicker(
    modifier: Modifier = Modifier,
    hoursText: TextFieldValue,
    onHoursChanged: (TextFieldValue) -> Unit,
    minutesText: TextFieldValue,
    onMinutesChanged: (TextFieldValue) -> Unit,
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
                    hasNext = true,
                    isFocused = areHoursFocused,
                    text = hoursText,
                    focusRequester = hoursFocusRequester,
                    onTextChanged = onHoursChanged,
                    onValidateInput = { text -> text.shouldBeInHoursRange() },
                    onNext = { minutesFocusRequester.requestFocus() },
                    onFocusChanged = {
                        if (it.isFocused && !areHoursFocused) {
                            onShowKeyboard()
                            // Move the cursor to the far right
                            onHoursChanged(
                                hoursText.copy(
                                    selection = TextRange(hoursText.text.length)
                                )
                            )
                        }
                        areHoursFocused = it.isFocused
                    }
                )

                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = ":",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 36.sp
                    )
                )

                AlarmTimeInputField(
                    modifier = Modifier.weight(1f),
                    hasNext = false,
                    isFocused = areMinutesFocused,
                    text = minutesText,
                    focusRequester = minutesFocusRequester,
                    onTextChanged = onMinutesChanged,
                    onValidateInput = { text -> text.shouldBeInMinutesRange() },
                    onDone = onDone,
                    onFocusChanged = {
                        if (it.isFocused && !areMinutesFocused) {
                            onShowKeyboard()
                            // Move the cursor to the far right
                            onMinutesChanged(
                                minutesText.copy(
                                    selection = TextRange(minutesText.text.length)
                                )
                            )
                        }
                        areMinutesFocused = it.isFocused
                    }
                )
            }
        }
    }
}

@Composable
private fun AlarmTimeInputField(
    modifier: Modifier = Modifier,
    text: TextFieldValue,
    hint: String = "00",
    isFocused: Boolean = false,
    hasNext: Boolean = false,
    onTextChanged: (TextFieldValue) -> Unit,
    focusRequester: FocusRequester? = null,
    onFocusChanged: (FocusState) -> Unit = {},
    onNext: () -> Unit = {},
    onDone: () -> Unit = {},
    onValidateInput: (String) -> Boolean = { true },
) {
    SnoozelooSurface(
        modifier = modifier,
        backgroundColor = MaterialTheme.colorScheme.surface
    ) {
        TimeInputField(
            hint = hint,
            hasNext = hasNext,
            isFocused = isFocused,
            text = text,
            focusRequester = focusRequester,
            onTextChanged = onTextChanged,
            onValidateInput = onValidateInput,
            onDone = onDone,
            onFocusChanged = onFocusChanged,
            onNext = onNext,
        )
    }
}

private fun String.shouldBeInHoursRange() = shouldBeInRange(0..23)
private fun String.shouldBeInMinutesRange() = shouldBeInRange(0..59)

private fun String.shouldBeInRange(range: IntRange): Boolean {
    val number = toIntOrNull()
    if (number == null || (number in range)) {
        return true
    }
    return false
}