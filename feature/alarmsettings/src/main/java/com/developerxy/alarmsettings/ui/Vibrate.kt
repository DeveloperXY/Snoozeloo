package com.developerxy.alarmsettings.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.developerxy.alarmsettings.R
import com.developerxy.designsystem.component.SnoozelooSurface
import com.developerxy.designsystem.component.SnoozelooSwitch

@Composable
fun Vibrate(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onToggleVibrate: () -> Unit
) {
    SnoozelooSurface(modifier = modifier) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.vibrate),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
            Spacer(modifier = Modifier.width(24.dp))
            SnoozelooSwitch(
                checked = enabled,
                onCheckedChange = onToggleVibrate
            )
        }
    }
}