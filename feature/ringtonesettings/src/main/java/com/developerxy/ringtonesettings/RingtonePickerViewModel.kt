package com.developerxy.ringtonesettings

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.developerxy.domain.RingtoneSoundPlayer
import com.developerxy.ringtonesettings.ui.model.Ringtone
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RingtonePickerViewModel : ViewModel() {

    private val _ringtones = MutableStateFlow<List<Ringtone>>(listOf())
    val ringtones = _ringtones.asStateFlow()

    private var ringtoneSoundPlayer = RingtoneSoundPlayer()

    fun setRingtones(ringtones: List<Ringtone>) {
        _ringtones.value = ringtones
    }

    fun playAlarmSoundPreview(ringtone: Ringtone, context: Context) {
        ringtoneSoundPlayer.playAlarmSound(ringtone.uri, context)
    }

    fun stopAlarmPreview() {
        ringtoneSoundPlayer.stopAlarmSound()
    }
}