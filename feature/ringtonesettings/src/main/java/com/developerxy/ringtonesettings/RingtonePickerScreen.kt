package com.developerxy.ringtonesettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developerxy.designsystem.component.SnoozelooIconButton
import com.developerxy.designsystem.icon.SnoozelooIcons
import com.developerxy.ringtonesettings.ui.RingtoneItem
import com.developerxy.ringtonesettings.ui.model.toUiModel
import com.developerxy.ui.RingtonesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapLatest
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun RingtonePickerScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    ringtonePickerViewModel: RingtonePickerViewModel = koinViewModel(),
    ringtonesViewModel: RingtonesViewModel = koinViewModel()
) {
    val domainRingtones by ringtonesViewModel.ringtones.collectAsState()
    LaunchedEffect(Unit) {
        ringtonesViewModel.selectedRingtone
            .mapLatest { selectedRingtone ->
                ringtonePickerViewModel.setRingtones(
                    domainRingtones.map { domainRingtone ->
                        domainRingtone.toUiModel(
                            isRingtoneSelected = domainRingtone == selectedRingtone
                        )
                    }
                )
            }.collect()
    }

    val context = LocalContext.current
    val ringtones by ringtonePickerViewModel.ringtones.collectAsState()

    Scaffold(modifier = modifier) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                SnoozelooIconButton(
                    icon = SnoozelooIcons.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    onClick = onBack
                )
                Spacer(modifier = Modifier.height(24.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(ringtones.size) {
                        RingtoneItem(
                            ringtone = ringtones[it],
                            onPressed = {
                                ringtonesViewModel.selectRingtone(ringtones[it].id)
                                ringtonePickerViewModel.playAlarmSoundPreview(ringtones[it], context)
                            }
                        )
                    }
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            ringtonePickerViewModel.stopAlarmPreview()
        }
    }
}

@Preview
@Composable
fun RingtoneSettingsScreenPreview(modifier: Modifier = Modifier) {
    RingtonePickerScreen()
}