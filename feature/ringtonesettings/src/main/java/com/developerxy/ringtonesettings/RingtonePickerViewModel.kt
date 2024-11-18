package com.developerxy.ringtonesettings

import androidx.lifecycle.ViewModel
import com.developerxy.ringtonesettings.ui.model.Ringtone
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RingtonePickerViewModel : ViewModel() {
    private val _ringtones = MutableStateFlow<List<Ringtone>>(listOf())
    val ringtones = _ringtones.asStateFlow()

    init {
        println("RingtonePickerViewModel created")
    }

    override fun onCleared() {
        println("RingtonePickerViewModel destroyed")
    }

    fun setRingtones(ringtones: List<Ringtone>) {
        _ringtones.value = ringtones
    }

    /*fun selectRingtone(ringtone: Ringtone) {
        _ringtones.value = _ringtones.value.map {
            if (it == ringtone) {
                it.copy(isSelected = true)
            } else {
                it.copy(isSelected = false) // Deselect all other ringtones
            }
        }
    }*/
}