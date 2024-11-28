package com.developerxy.snoozeloo

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.developerxy.alarmtrigger.AlarmTriggerActivity
import com.developerxy.alarmtrigger.domain.GetAlarmDetailsUseCase
import com.developerxy.formatAsDisplayTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class AlarmReceiver : BroadcastReceiver(), KoinComponent {

    private val getAlarmDetails: GetAlarmDetailsUseCase by inject()

    override fun onReceive(context: Context, intent: Intent) {
        val alarmId = intent.getIntExtra("alarm_id", -1)

        if (alarmId != -1) {
            showFullScreenNotification(context, alarmId)
        }
    }

    fun showFullScreenNotification(context: Context, alarmId: Int) {
        val notificationId = 1
        val channelId = "full_screen_channel"

        // Create the notification channel (for API 26+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Full Screen Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for full-screen notifications"
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val alarm = getAlarmDetails(id = alarmId)

            withContext(Dispatchers.Main) {
                // Create the full-screen intent (to launch the activity)
                val intent = Intent(
                    context,
                    AlarmTriggerActivity::class.java
                ).apply {
                    putExtra("alarm", alarm)
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                val fullScreenPendingIntent =
                    PendingIntent.getActivity(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    )

                val notification = NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(android.R.drawable.ic_dialog_alert)
                    .setContentTitle("Alarm at ${formatAsDisplayTime(alarm.time.hours, alarm.time.minutes)}")
                    .setContentText("Alarm at ${formatAsDisplayTime(alarm.time.hours, alarm.time.minutes)}: ${alarm.name}")
                    .setPriority(NotificationCompat.PRIORITY_HIGH) // High priority for heads-up notifications
                    .setCategory(NotificationCompat.CATEGORY_ALARM) // Mark as an alarm
                    .setFullScreenIntent(fullScreenPendingIntent, true) // Full-screen intent
                    .setAutoCancel(true) // Dismiss notification on click
                    .build()

                // Show the notification
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    NotificationManagerCompat.from(context).notify(notificationId, notification)
                } else {
                    throw IllegalStateException("POST_NOTIFICATIONS permission not granted")
                }
            }
        }
    }
}