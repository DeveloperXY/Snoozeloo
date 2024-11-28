package com.developerxy.alarmtrigger.ui

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.developerxy.alarmtrigger.ui.model.Alarm
import com.developerxy.domain.RingtoneSoundPlayer

class AlarmTriggerViewModel : ViewModel() {

    private val _alarm = mutableStateOf<Alarm?>(null)
    private val ringtoneSoundPlayer = RingtoneSoundPlayer()

    fun setCurrentAlarm(alarm: Alarm, context: Context) {
        _alarm.value = alarm
        ringtoneSoundPlayer.playAlarmSound(Uri.parse(alarm.ringtoneUri.orEmpty()), context)
    }
}