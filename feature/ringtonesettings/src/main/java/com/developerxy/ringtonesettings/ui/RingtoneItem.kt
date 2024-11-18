package com.developerxy.ringtonesettings.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developerxy.designsystem.component.SnoozelooSurface
import com.developerxy.designsystem.icon.SnoozelooIcons
import com.developerxy.ringtonesettings.R
import com.developerxy.ringtonesettings.ui.model.Ringtone

@Composable
fun RingtoneItem(
    modifier: Modifier = Modifier,
    ringtone: Ringtone? = null,
    onPressed: () -> Unit
) {
    SnoozelooSurface(modifier = Modifier
        .fillMaxWidth()
        .clickable { onPressed() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(30.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    imageVector = if (ringtone == null) SnoozelooIcons.Silent else SnoozelooIcons.Bell,
                    contentDescription = "Silent"
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = ringtone?.name ?: stringResource(R.string.silent),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )

            if (ringtone?.isSelected == true) {
                Box(
                    modifier = Modifier
                        .requiredSize(24.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        imageVector = SnoozelooIcons.Check,
                        contentDescription = "Silent"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RingtoneItemPreview(modifier: Modifier = Modifier) {
    RingtoneItem(onPressed = {})
}