package com.developerxy.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
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
    val textStyle = MaterialTheme.typography.labelMedium.copy(
        fontSize = 52.sp,
        lineHeight = 62.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary
    )
    val showHint = text.isEmpty() && !isFocused

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
                .onFocusChanged(onFocusChanged)
                .then(
                    if (focusRequester != null) Modifier.focusRequester(focusRequester)
                    else Modifier
                ),
            value = text,
            onValueChange = {
                val input = it.text
                if (onValidateInput(input)) {
                    val paddedInput = input.toPaddedTimeValue()
                    onTextChanged(
                        it.copy(
                            text = paddedInput,
                            selection = TextRange(paddedInput.length)
                        )
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = if (hasNext) ImeAction.Next else ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onDone() },
                onNext = { onNext() }
            ),
            textStyle = textStyle,
        )
    }
}

private fun TextFieldValue.isEmpty() = this.text.isEmpty()
private fun String.toPaddedTimeValue() = padStart(2, '0')