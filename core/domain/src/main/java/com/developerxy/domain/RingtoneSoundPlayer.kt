package com.developerxy.domain

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri

class RingtoneSoundPlayer {
    private val mediaPlayer = MediaPlayer()

    fun playAlarmSound(ringtoneUri: Uri, context: Context) {
        mediaPlayer.apply {
            reset()
            if (ringtoneUri == Uri.EMPTY) {
                return
            }

            setDataSource(context, ringtoneUri)
            isLooping = true
            prepare()
            start()
        }
    }

    fun stopAlarmSound() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }

        mediaPlayer.release()
    }
}