package com.developerxy.snoozeloo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.developerxy.alarmsettings.AlarmSettingsScreen
import com.developerxy.ui.NavRoutes
import com.developerxy.youralarms.YourAlarmsScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainAppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.YOUR_ALARMS) {
        addYourAlarms(navController)
        addAlarmSettings(navController)
    }
}

private fun NavGraphBuilder.addYourAlarms(navController: NavHostController) {
    composable(NavRoutes.YOUR_ALARMS) {
        YourAlarmsScreen(
            onCreateNewAlarm = {
                navController.navigate(NavRoutes.ALARM_SETTINGS)
            }
        )
    }
}

private fun NavGraphBuilder.addAlarmSettings(navController: NavHostController) {
    composable(NavRoutes.ALARM_SETTINGS) {
        AlarmSettingsScreen(
            viewModel = koinViewModel(),
            navigateBack = {
                navController.popBackStack()
            }
        )
    }
}