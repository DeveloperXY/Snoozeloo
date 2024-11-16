package com.developerxy.alarmsettings.ui.dialog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.developerxy.alarmsettings.R
import com.developerxy.designsystem.component.SnoozelooFilledButton
import com.developerxy.designsystem.component.SnoozelooSurface

@Composable
fun AlarmNameDialog(
    modifier: Modifier = Modifier,
    initialAlarmName: String,
    onAlarmNameChanged: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var alarmName by remember { mutableStateOf(initialAlarmName) }

    Dialog(onDismissRequest = { onDismiss() }) {
        SnoozelooSurface(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.alarm_name),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                AlarmNameTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = alarmName,
                    onTextChanged = { alarmName = it }
                )
                Spacer(modifier = Modifier.height(10.dp))
                SnoozelooFilledButton(
                    modifier = Modifier.align(Alignment.End),
                    text = stringResource(R.string.save),
                    onClick = {
                        onDismiss()
                        onAlarmNameChanged(alarmName)
                    }
                )
            }
        }
    }
}

@Composable
private fun AlarmNameTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(vertical = 10.dp, horizontal = 12.dp)
    ) {
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = onTextChanged,
            singleLine = true
        )
    }
}