package com.developerxy.youralarms.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developerxy.youralarms.ui.model.Alarm

@Composable
fun AlarmsList(
    modifier: Modifier = Modifier,
    alarms: List<Alarm>
) {
    LazyColumn {
        items(alarms.size) {
            Alarm(modifier = modifier.padding(horizontal = 16.dp))
        }
    }
}

@Preview
@Composable
fun AlarmsListPreview() {
    AlarmsList(alarms = listOf())
}