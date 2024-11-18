package com.developerxy.ringtonesettings.ui.model

import com.developerxy.model.RingtoneInfo

data class Ringtone(
    val id: String,
    val name: String,
    val isSelected: Boolean = false
)

fun RingtoneInfo.toUiModel(
    isRingtoneSelected: Boolean = false
): Ringtone {
    return Ringtone(id = id, name = title, isSelected = isRingtoneSelected)
}