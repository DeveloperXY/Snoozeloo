package com.developerxy.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.developerxy.model.Alarm
import com.developerxy.model.AlarmTime

@Entity(tableName = "alarms")
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    @Embedded
    val time: AlarmTimeEntity,
    val selectedDays: Byte,
    val volume: Int,
    val vibrate: Boolean,
    val ringtone: String = "Default"
)

data class AlarmTimeEntity(
    val hours: Int,
    val minutes: Int,
)

fun AlarmEntity.asDomainModel() = Alarm(
    id = id,
    name = name,
    time = AlarmTime(hours = time.hours, minutes = time.minutes),
    selectedDays = selectedDays,
    volume = volume,
    vibrate = vibrate,
    ringtone = ringtone
)