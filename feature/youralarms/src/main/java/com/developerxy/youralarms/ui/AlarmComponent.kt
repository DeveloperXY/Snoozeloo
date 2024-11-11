package com.developerxy.youralarms.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developerxy.designsystem.component.SnoozelooChip
import com.developerxy.designsystem.component.SnoozelooSurface
import com.developerxy.designsystem.component.SnoozelooSwitch
import com.developerxy.designsystem.theme.SnoozelooTextGray

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Alarm(modifier: Modifier = Modifier) {
    SnoozelooSurface(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Wake Up",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                SnoozelooSwitch()
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "10:00",
                    modifier = Modifier.alignByBaseline(),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 42.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "AM",
                    modifier = Modifier.alignByBaseline(),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Alarm in 30min", style = MaterialTheme.typography.labelMedium.copy(
                    color = SnoozelooTextGray, fontWeight = FontWeight.Medium, fontSize = 14.sp
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                var firstChecked by rememberSaveable { mutableStateOf(false) }
                SnoozelooChip(text = "Mo",
                    selected = firstChecked,
                    onClick = { firstChecked = !firstChecked })
                var secondChecked by rememberSaveable { mutableStateOf(true) }
                SnoozelooChip(text = "Tu",
                    selected = secondChecked,
                    onClick = { secondChecked = !secondChecked })
                var thirdChecked by rememberSaveable { mutableStateOf(false) }
                SnoozelooChip(text = "We",
                    selected = thirdChecked,
                    onClick = { thirdChecked = !thirdChecked })
                var fourthChecked by rememberSaveable { mutableStateOf(true) }
                SnoozelooChip(text = "Th",
                    selected = fourthChecked,
                    onClick = { fourthChecked = !fourthChecked })
                var fifthChecked by rememberSaveable { mutableStateOf(false) }
                SnoozelooChip(text = "Fr",
                    selected = fifthChecked,
                    onClick = { fifthChecked = !fifthChecked })
                var sixthChecked by rememberSaveable { mutableStateOf(true) }
                SnoozelooChip(text = "Sa",
                    selected = sixthChecked,
                    onClick = { sixthChecked = !sixthChecked })
                var seventhChecked by rememberSaveable { mutableStateOf(false) }
                SnoozelooChip(text = "Su",
                    selected = seventhChecked,
                    onClick = { seventhChecked = !seventhChecked })
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Go to bed at 02:00AM to get 8h of sleep",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = SnoozelooTextGray, fontWeight = FontWeight.Medium, fontSize = 14.sp
                )
            )
        }
    }
}

@Preview
@Composable
fun AlarmPreview() {
    Alarm()
}