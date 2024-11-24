package com.developerxy.snoozeloo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.developerxy.designsystem.theme.AppTheme
import com.developerxy.snoozeloo.ui.navigation.MainAppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        var keepSplashScreen = true
        super.onCreate(savedInstanceState)
        /*splashscreen.setKeepOnScreenCondition { keepSplashScreen }
        lifecycleScope.launch {
            delay(2_000)
            keepSplashScreen = false
        }*/

        enableEdgeToEdge()
        setContent {
            AppTheme(dynamicColor = false) {
                val navHostController = rememberNavController()
                MainAppNavigation(navController = navHostController)
            }
        }
    }
}