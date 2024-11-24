package com.developerxy.alarmtrigger

import android.os.Bundle
import android.util.Log
import androidx.compose.material3.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class FullScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Scaffold { padding ->
                    Text("This is a full-screen notification!")
                }
            }
        }

        Log.d("FullScreenActivity", "Full-Screen Activity Launched")
    }
}