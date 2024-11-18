package com.developerxy.ringtonesettings

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.developerxy.ringtonesettings.ui.model.Ringtone
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RingtonePickerViewModel : ViewModel() {

    private val _ringtones = MutableStateFlow<List<Ringtone>>(listOf())
    val ringtones = _ringtones.asStateFlow()

    private var mediaPlayer = MediaPlayer()

    fun setRingtones(ringtones: List<Ringtone>) {
        _ringtones.value = ringtones
    }

    fun playAlarmSoundPreview(ringtone: Ringtone, context: Context) {
        mediaPlayer.apply {
            reset()
            if (ringtone.uri == Uri.EMPTY) {
                return
            }

            setDataSource(context, ringtone.uri)
            isLooping = true
            prepare()
            start()
        }
    }

    fun stopAlarmPreview() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }

        mediaPlayer.release()
    }

    override fun onCleared() {
        stopAlarmPreview()
    }
}