package com.developerxy.youralarms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerxy.youralarms.domain.FetchYourAlarmsUseCase
import com.developerxy.youralarms.domain.ToggleAlarmActiveStateUseCase
import com.developerxy.youralarms.ui.model.Alarm
import com.developerxy.youralarms.ui.model.asDomainModel
import com.developerxy.youralarms.ui.model.asUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class YourAlarmsViewModel(
    private val _fetchYourAlarms: FetchYourAlarmsUseCase,
    private val _toggleAlarmActiveState: ToggleAlarmActiveStateUseCase,
) : ViewModel() {
    private val _alarms = MutableStateFlow<List<Alarm>>(emptyList())
    val alarms: StateFlow<List<Alarm>> = _alarms.asStateFlow()

    fun loadAlarms() {
        viewModelScope.launch(Dispatchers.IO) {
            _fetchYourAlarms().map {
                it.map { it.asUiModel() }
            }.collect { alarms ->
                withContext(Dispatchers.Main) {
                    _alarms.value = alarms
                }
            }
        }
    }

    fun toggleAlarmActiveState(alarm: Alarm) {
        viewModelScope.launch {
            _toggleAlarmActiveState(alarm.asDomainModel())
        }
    }
}