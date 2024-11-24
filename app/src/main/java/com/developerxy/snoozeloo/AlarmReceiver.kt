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
import com.developerxy.alarmtrigger.FullScreenActivity


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        showFullScreenNotification(context)
    }

    fun showFullScreenNotification(context: Context) {
        val notificationId = 1
        val channelId = "full_screen_channel"

        // Create the full-screen intent (to launch the activity)
        val intent = Intent(
            context,
            FullScreenActivity::class.java
        )
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val fullScreenPendingIntent =
            PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

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

        // Build the notification
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle("Incoming Alert")
            .setContentText("This is a full-screen notification.")
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