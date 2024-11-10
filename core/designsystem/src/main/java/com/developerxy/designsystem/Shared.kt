package com.developerxy.designsystem

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class ButtonSize(val height: Dp, val fontSize: TextUnit, val horizontalPadding: Dp) {
    MEDIUM(height = 32.dp, fontSize = 16.sp, horizontalPadding = 16.dp),
    LARGE(height = 46.dp, fontSize = 24.sp, horizontalPadding = 32.dp);

    operator fun component1() = height
    operator fun component2() = fontSize
    operator fun component3() = horizontalPadding
}