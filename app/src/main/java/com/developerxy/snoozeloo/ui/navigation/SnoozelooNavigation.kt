package com.developerxy.snoozeloo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.developerxy.alarmsettings.AlarmSettingsScreen
import com.developerxy.ringtonesettings.RingtonePickerScreen
import com.developerxy.ui.NavRoute
import com.developerxy.ui.RingtonesViewModel
import com.developerxy.youralarms.YourAlarmsScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainAppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.YOUR_ALARMS
    ) {
        addYourAlarms(navController)
        addAlarmSettings(navController)
    }
}

private fun NavGraphBuilder.addYourAlarms(navController: NavHostController) {
    composable(NavRoute.YOUR_ALARMS) {
        YourAlarmsScreen(
            onCreateNewAlarm = {
                navController.navigate(NavRoute.ALARM_SETTINGS)
            }
        )
    }
}

private fun NavGraphBuilder.addAlarmSettings(navController: NavHostController) {
    navigation(
        startDestination = NavRoute.ALARM_SETTINGS_HOME_PAGE,
        route = NavRoute.ALARM_SETTINGS
    ) {
        composable(NavRoute.ALARM_SETTINGS_HOME_PAGE) {
            val ringtonesViewModel =
                it.sharedViewModel<RingtonesViewModel>(navController)
            AlarmSettingsScreen(
                ringtonesViewModel = ringtonesViewModel,
                navigateBack = {
                    navController.popBackStack()
                },
                onShowRingtonesList = {
                    navController.navigate(NavRoute.ALARM_SETTINGS_RINGTONE_PICKER)
                }
            )
        }
        composable(NavRoute.ALARM_SETTINGS_RINGTONE_PICKER) {
            val ringtonesViewModel =
                it.sharedViewModel<RingtonesViewModel>(navController)
            RingtonePickerScreen(
                ringtonesViewModel = ringtonesViewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}