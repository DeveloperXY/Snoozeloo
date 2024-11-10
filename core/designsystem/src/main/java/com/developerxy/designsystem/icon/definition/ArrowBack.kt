package com.developerxy.designsystem.icon.definition

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ArrowBack: ImageVector
    get() {
        if (_ArrowBack != null) {
            return _ArrowBack!!
        }
        _ArrowBack = ImageVector.Builder(
            name = "ArrowBack",
            defaultWidth = 16.dp,
            defaultHeight = 12.dp,
            viewportWidth = 16f,
            viewportHeight = 12f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFF6F6F6)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(0.751f, 6.849f)
                curveTo(0.283f, 6.38f, 0.283f, 5.62f, 0.751f, 5.151f)
                lineTo(5.551f, 0.351f)
                curveTo(6.02f, -0.117f, 6.78f, -0.117f, 7.248f, 0.351f)
                curveTo(7.717f, 0.82f, 7.717f, 1.58f, 7.248f, 2.049f)
                lineTo(4.497f, 4.8f)
                lineTo(14.4f, 4.8f)
                curveTo(15.063f, 4.8f, 15.6f, 5.337f, 15.6f, 6f)
                curveTo(15.6f, 6.663f, 15.063f, 7.2f, 14.4f, 7.2f)
                lineTo(4.497f, 7.2f)
                lineTo(7.248f, 9.951f)
                curveTo(7.717f, 10.42f, 7.717f, 11.18f, 7.248f, 11.649f)
                curveTo(6.78f, 12.117f, 6.02f, 12.117f, 5.551f, 11.649f)
                lineTo(0.751f, 6.849f)
                close()
            }
        }.build()

        return _ArrowBack!!
    }

@Suppress("ObjectPropertyName")
private var _ArrowBack: ImageVector? = null
