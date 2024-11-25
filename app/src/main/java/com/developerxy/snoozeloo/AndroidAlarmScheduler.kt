package com.developerxy.snoozeloo

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.developerxy.data.AlarmScheduler
import com.developerxy.model.Alarm
import java.time.ZoneOffset

class AndroidAlarmScheduler(private val context: Context) : AlarmScheduler {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    @SuppressLint("ScheduleExactAlarm")
    override fun schedule(alarm: Alarm) {
        val pendingIntent = alarm.asPendingIntent()
        val nextTriggerDateTime = alarm.nextTriggerDateTime()

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            nextTriggerDateTime.toEpochSecond(ZoneOffset.UTC),
            pendingIntent
        )
    }

    override fun cancel(alarm: Alarm) {
        alarmManager.cancel(alarm.asPendingIntent())
    }

    private fun Alarm.asPendingIntent() = PendingIntent.getBroadcast(
        context,
        id,
        Intent(context, AlarmReceiver::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
}