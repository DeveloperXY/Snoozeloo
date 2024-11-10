@file:OptIn(ExperimentalLayoutApi::class)

package com.developerxy.app.snoozeloo.catalog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developerxy.designsystem.ButtonSize
import com.developerxy.designsystem.SnoozelooButtonDefaults
import com.developerxy.designsystem.component.SnoozelooChip
import com.developerxy.designsystem.component.SnoozelooFilledButton
import com.developerxy.designsystem.component.SnoozelooIconButton
import com.developerxy.designsystem.component.SnoozelooOutlinedButton
import com.developerxy.designsystem.component.SnoozelooSwitch
import com.developerxy.designsystem.component.SwitchSize
import com.developerxy.designsystem.icon.SnoozelooIcons
import com.developerxy.designsystem.theme.AppTheme

@Composable
fun SnoozelooCatalog() {
    AppTheme(dynamicColor = false) {
        Surface {
            val contentPadding = WindowInsets
                .systemBars
                .add(WindowInsets(left = 16.dp, top = 16.dp, right = 16.dp, bottom = 16.dp))
                .asPaddingValues()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFFFFF)),
                contentPadding = contentPadding,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item {
                    Text(
                        text = "Snoozeloo Catalog",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
                item { Text("Switches", Modifier.padding(top = 16.dp)) }
                item {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        var firstChecked by rememberSaveable { mutableStateOf(true) }
                        SnoozelooSwitch(
                            size = SwitchSize.MEDIUM,
                            checked = firstChecked,
                            onCheckedChange = { firstChecked = !firstChecked }
                        )
                        var secondChecked by rememberSaveable { mutableStateOf(false) }
                        SnoozelooSwitch(
                            checked = secondChecked,
                            onCheckedChange = { secondChecked = !secondChecked }
                        )
                        var thirdChecked by rememberSaveable { mutableStateOf(true) }
                        SnoozelooSwitch(
                            size = SwitchSize.SMALL,
                            checked = thirdChecked,
                            onCheckedChange = { thirdChecked = !thirdChecked }
                        )
                        var fourthChecked by rememberSaveable { mutableStateOf(false) }
                        SnoozelooSwitch(
                            size = SwitchSize.SMALL,
                            checked = fourthChecked,
                            onCheckedChange = { fourthChecked = !fourthChecked }
                        )
                    }
                }
                item { Text("Chips", Modifier.padding(top = 16.dp)) }
                item {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        var firstChecked by rememberSaveable { mutableStateOf(false) }
                        SnoozelooChip(
                            text = "Mo",
                            selected = firstChecked,
                            onClick = { firstChecked = !firstChecked }
                        )
                        var secondChecked by rememberSaveable { mutableStateOf(true) }
                        SnoozelooChip(
                            text = "Tu",
                            selected = secondChecked,
                            onClick = { secondChecked = !secondChecked }
                        )
                        var thirdChecked by rememberSaveable { mutableStateOf(false) }
                        SnoozelooChip(
                            text = "We",
                            selected = thirdChecked,
                            onClick = { thirdChecked = !thirdChecked }
                        )
                        var fourthChecked by rememberSaveable { mutableStateOf(true) }
                        SnoozelooChip(
                            text = "Th",
                            selected = fourthChecked,
                            onClick = { fourthChecked = !fourthChecked }
                        )
                        var fifthChecked by rememberSaveable { mutableStateOf(false) }
                        SnoozelooChip(
                            text = "Fr",
                            selected = fifthChecked,
                            onClick = { fifthChecked = !fifthChecked }
                        )
                        var sixthChecked by rememberSaveable { mutableStateOf(true) }
                        SnoozelooChip(
                            text = "Sa",
                            selected = sixthChecked,
                            onClick = { sixthChecked = !sixthChecked }
                        )
                        var seventhChecked by rememberSaveable { mutableStateOf(false) }
                        SnoozelooChip(
                            text = "Su",
                            selected = seventhChecked,
                            onClick = { seventhChecked = !seventhChecked }
                        )
                    }
                }
                item { Text("Buttons", Modifier.padding(top = 16.dp)) }
                item {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        SnoozelooIconButton(
                            enabled = false,
                            icon = SnoozelooIcons.Close,
                            contentDescription = "Close"
                        )
                        SnoozelooIconButton(
                            enabled = true,
                            icon = SnoozelooIcons.ArrowBack,
                            contentDescription = "Go back"
                        )
                        SnoozelooFilledButton(
                            enabled = true,
                            text = "Save"
                        ) { }
                        SnoozelooFilledButton(
                            enabled = false,
                            text = "Save"
                        ) { }
                        SnoozelooFilledButton(
                            enabled = true,
                            size = ButtonSize.LARGE,
                            text = "Save"
                        ) { }
                        SnoozelooFilledButton(
                            enabled = false,
                            size = ButtonSize.LARGE,
                            text = "Save"
                        ) { }
                        SnoozelooOutlinedButton(
                            enabled = true,
                            size = ButtonSize.LARGE,
                            text = "Save",
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SnoozelooCatalogPreview() {
    SnoozelooCatalog()
}