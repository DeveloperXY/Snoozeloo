package com.developerxy.ui

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerxy.domain.FetchDefaultRingtonesUseCase
import com.developerxy.model.RingtoneInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

val silentRingtone = RingtoneInfo(id = "snoozeloo_silent_ringtone_id", title = "Silent", uri = Uri.EMPTY)

class RingtonesViewModel(
    private val fetchDefaultRingtonesUseCase: FetchDefaultRingtonesUseCase
) : ViewModel() {
    private val _selectedRingtone = MutableStateFlow(silentRingtone)
    val selectedRingtone = _selectedRingtone.asStateFlow()

    private val _ringtones = MutableStateFlow<List<RingtoneInfo>>(listOf())
    val ringtones = _ringtones.asStateFlow()

    init {
        loadRingtones()
    }

    fun loadRingtones() {
        viewModelScope.launch(Dispatchers.IO) {
            _ringtones.value = fetchDefaultRingtonesUseCase()
                .toMutableList()
                .apply {
                    add(0, silentRingtone)
                }
        }
    }

    fun selectRingtone(ringtoneId: String) {
        _selectedRingtone.value = _ringtones.value.single { it.id == ringtoneId }
    }
}