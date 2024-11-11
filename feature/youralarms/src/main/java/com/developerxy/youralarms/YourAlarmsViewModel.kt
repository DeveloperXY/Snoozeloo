package com.developerxy.youralarms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerxy.youralarms.domain.FetchYourAlarmsUseCase
import com.developerxy.youralarms.ui.model.Alarm
import com.developerxy.youralarms.ui.model.asUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class YourAlarmsViewModel(
    private val fetchYourAlarms: FetchYourAlarmsUseCase
) : ViewModel() {
    private val _alarms = MutableStateFlow<List<Alarm>>(emptyList())
    val alarms: StateFlow<List<Alarm>> = _alarms.asStateFlow()

    private var alarmCollectionJob: Job? = null

    fun loadAlarms() {
        alarmCollectionJob = viewModelScope.launch(Dispatchers.IO) {
            fetchYourAlarms().map {
                it.map { it.asUiModel() }
            }.collect { alarms ->
                withContext(Dispatchers.Main) {
                    _alarms.value = alarms
                }
            }
        }
    }

    override fun onCleared() {
        alarmCollectionJob?.cancel()
    }
}