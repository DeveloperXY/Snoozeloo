package com.developerxy.ringtonesettings.ui.model

import android.net.Uri
import com.developerxy.model.RingtoneInfo

data class Ringtone(
    val id: String,
    val name: String,
    val uri: Uri,
    val isSelected: Boolean = false
)

fun RingtoneInfo.toUiModel(
    isRingtoneSelected: Boolean = false
): Ringtone {
    return Ringtone(id = id, name = title, uri = uri, isSelected = isRingtoneSelected)
}