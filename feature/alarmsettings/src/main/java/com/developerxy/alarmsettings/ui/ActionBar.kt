package com.developerxy.alarmsettings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.developerxy.alarmsettings.R
import com.developerxy.designsystem.component.SnoozelooFilledButton
import com.developerxy.designsystem.component.SnoozelooIconButton
import com.developerxy.designsystem.icon.SnoozelooIcons

@Composable
internal fun ActionBar(
    modifier: Modifier = Modifier,
    closeEnabled: Boolean = false,
    saveEnabled: Boolean = false,
    onClose: () -> Unit = {},
    onSave: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SnoozelooIconButton(
            enabled = closeEnabled,
            icon = SnoozelooIcons.Close,
            contentDescription = stringResource(R.string.close),
            onClick = onClose
        )
        SnoozelooFilledButton(
            enabled = saveEnabled,
            text = stringResource(R.string.save),
            onClick = onSave
        )
    }
}