package com.developerxy.alarmsettings.ui

import SnoozelooSlider
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.developerxy.alarmsettings.R
import com.developerxy.designsystem.component.SnoozelooSurface

@Composable
fun AlarmVolume(
    modifier: Modifier = Modifier,
    volume: Int,
    onVolumeChanged: (Int) -> Unit
) {

    SnoozelooSurface(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.alarm_volume),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            SnoozelooSlider(
                modifier = Modifier.fillMaxWidth(),
                initialValue = volume.toFloat() / 100,
                onValueChange = {
                    onVolumeChanged((it * 100).toInt())
                }
            )
        }
    }
}