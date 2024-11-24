package com.developerxy.ui

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

@RequiresApi(Build.VERSION_CODES.S)
class ExactAlarmPermissionReceiver(
    private val onPermissionChanged: (Boolean) -> Unit
) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        onPermissionChanged(context.canScheduleExactAlarms())
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun RegisterExactAlarmPermissionReceiver(onPermissionChanged: (Boolean) -> Unit) {
    val context = LocalContext.current

    DisposableEffect(context) {
        val receiver = ExactAlarmPermissionReceiver(onPermissionChanged)
        val intentFilter =
            IntentFilter(AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED)
        context.registerReceiver(receiver, intentFilter)

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }
}

fun Context.canScheduleExactAlarms(): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        return alarmManager.canScheduleExactAlarms()
    }

    // Apps targeting API 30 and below do not need this permission to use exact alarm APIs.
    // https://developer.android.com/reference/android/Manifest.permission#SCHEDULE_EXACT_ALARM
    return true
}