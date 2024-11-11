package com.developerxy.youralarms.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AlarmsList(modifier: Modifier = Modifier) {
    Column {
        Alarm(modifier = modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Alarm(modifier = modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Alarm(modifier = modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Alarm(modifier = modifier.padding(horizontal = 16.dp))
        Alarm(modifier = modifier.padding(horizontal = 16.dp))
    }
}

@Preview
@Composable
fun AlarmsListPreview() {
    AlarmsList()
}