package com.developerxy.alarmtrigger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.developerxy.alarmtrigger.ui.AlarmTriggerScreen
import com.developerxy.designsystem.theme.AppTheme

class AlarmTriggerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(dynamicColor = false) {
                AlarmTriggerScreen()
            }
        }
    }
}