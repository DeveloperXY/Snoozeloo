package com.developerxy.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimeInputField(
    modifier: Modifier = Modifier,
    value: Int?,
    hint: String = "00",
    isFocused: Boolean = false,
    imeAction: ImeAction,
    keyboardActions: KeyboardActions,
    onValueChanged: (Int?) -> Unit,
    focusRequester: FocusRequester? = null,
    onFocusChanged: (Boolean) -> Unit = {},
    onValidateInput: (String) -> Boolean = { true },
) {
    val textStyle = MaterialTheme.typography.labelMedium.copy(
        fontSize = 52.sp,
        lineHeight = 62.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary
    )
    val showHint = value == null && !isFocused

    Box(
        modifier = modifier
            .clickable(enabled = !isFocused) { focusRequester?.requestFocus() }
            .padding(horizontal = 30.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (showHint) {
            Text(
                text = hint,
                style = textStyle.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }

        BasicTextField(
            modifier = Modifier
                .alpha(if (showHint) 0f else 1f)
                .onFocusChanged {
                    if (it.isFocused && !isFocused) {
                        // Move the cursor to the far right
                        onValueChanged(value)
                    }

                    onFocusChanged(it.isFocused)
                }
                .then(
                    if (focusRequester != null) Modifier.focusRequester(focusRequester)
                    else Modifier
                ),
            value = run {
                val stringValue = value?.toString().orEmpty()
                TextFieldValue(
                    if (isFocused) stringValue else stringValue.toPaddedTimeValue(),
                    selection = TextRange(stringValue.length)
                )
            },
            onValueChange = {
                val input = it.text

                if (onValidateInput(input)) {
                    onValueChanged(
                        input.toIntOrNull()
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions,
            textStyle = textStyle,
        )
    }
}

private fun String.toPaddedTimeValue() = if (isEmpty()) this else padStart(2, '0')