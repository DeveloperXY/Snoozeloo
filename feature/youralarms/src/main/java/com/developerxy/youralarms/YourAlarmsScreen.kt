package com.developerxy.youralarms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.developerxy.youralarms.ui.AlarmsList
import org.koin.androidx.compose.koinViewModel

@Composable
fun YourAlarmsScreen(
    modifier: Modifier = Modifier,
    viewModel: YourAlarmsViewModel = koinViewModel()
) {
    val alarms by viewModel.alarms.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadAlarms()
    }

    Scaffold { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    color = MaterialTheme.colorScheme.surface
                )
        ) {
            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                ),
                text = stringResource(R.string.your_alarms),
                style = MaterialTheme.typography.titleMedium
                    .copy(
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
            )
            Spacer(modifier = Modifier.height(24.dp))
            AlarmsList(alarms = alarms)
        }
    }
}

@Preview
@Composable
fun YourAlarmsScreenPreview(modifier: Modifier = Modifier) {
    YourAlarmsScreen()
}