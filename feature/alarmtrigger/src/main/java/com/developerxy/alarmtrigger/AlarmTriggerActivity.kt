package com.developerxy.alarmtrigger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.developerxy.alarmtrigger.ui.AlarmTriggerScreen
import com.developerxy.alarmtrigger.ui.AlarmTriggerViewModel
import com.developerxy.alarmtrigger.ui.model.asUiModel
import com.developerxy.designsystem.theme.AppTheme
import com.developerxy.model.Alarm
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class AlarmTriggerActivity : ComponentActivity(), KoinComponent {

    private val alarmTriggerViewModel: AlarmTriggerViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val alarm = intent.getParcelableExtra<Alarm>("alarm")
            ?: throw IllegalArgumentException("Expected an alarm object but got null")

        alarm.asUiModel()
            .also {
                alarmTriggerViewModel.setCurrentAlarm(it, this)
                setContent {
                    AppTheme(dynamicColor = false) {
                        AlarmTriggerScreen(alarm = it)
                    }
                }
        }
    }
}