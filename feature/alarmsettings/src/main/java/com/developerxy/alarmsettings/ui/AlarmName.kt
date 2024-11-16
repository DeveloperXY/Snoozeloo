package com.developerxy.alarmsettings.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.developerxy.alarmsettings.R
import com.developerxy.designsystem.component.SnoozelooSurface

@Composable
fun AlarmName(
    modifier: Modifier = Modifier,
    text: String
) {
    SnoozelooSurface(modifier = modifier) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .alignByBaseline(),
                text = stringResource(R.string.alarm_name),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                modifier = Modifier
                    .weight(1f)
                    .alignByBaseline(),
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.End
                )
            )
        }
    }
}