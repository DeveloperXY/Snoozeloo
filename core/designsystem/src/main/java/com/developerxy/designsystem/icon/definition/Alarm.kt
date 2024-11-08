package com.developerxy.designsystem.icon.definition

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val Alarm: ImageVector
    get() {
        if (_Alarm != null) {
            return _Alarm!!
        }
        _Alarm = ImageVector.Builder(
            name = "Alarm",
            defaultWidth = 82.dp,
            defaultHeight = 82.dp,
            viewportWidth = 82f,
            viewportHeight = 82f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(25.615f, 9.181f)
                curveTo(26.912f, 8.613f, 27.504f, 7.103f, 26.937f, 5.806f)
                curveTo(26.37f, 4.509f, 24.859f, 3.918f, 23.562f, 4.485f)
                curveTo(15.851f, 7.857f, 9.309f, 13.393f, 4.7f, 20.326f)
                curveTo(3.916f, 21.504f, 4.236f, 23.095f, 5.415f, 23.878f)
                curveTo(6.593f, 24.662f, 8.184f, 24.342f, 8.968f, 23.163f)
                curveTo(13.038f, 17.042f, 18.815f, 12.154f, 25.615f, 9.181f)
                close()
                moveTo(58.439f, 4.485f)
                curveTo(57.142f, 3.918f, 55.631f, 4.509f, 55.064f, 5.806f)
                curveTo(54.497f, 7.103f, 55.089f, 8.613f, 56.385f, 9.181f)
                curveTo(63.186f, 12.154f, 68.963f, 17.042f, 73.033f, 23.163f)
                curveTo(73.817f, 24.342f, 75.407f, 24.662f, 76.586f, 23.878f)
                curveTo(77.764f, 23.095f, 78.085f, 21.504f, 77.301f, 20.326f)
                curveTo(72.692f, 13.393f, 66.149f, 7.857f, 58.439f, 4.485f)
                close()
                moveTo(66.452f, 64.562f)
                curveTo(70.839f, 59.027f, 73.459f, 52.028f, 73.459f, 44.416f)
                curveTo(73.459f, 26.49f, 58.926f, 11.958f, 41f, 11.958f)
                curveTo(23.074f, 11.958f, 8.542f, 26.49f, 8.542f, 44.416f)
                curveTo(8.542f, 52.027f, 11.161f, 59.027f, 15.548f, 64.562f)
                lineTo(8.26f, 73.553f)
                curveTo(7.368f, 74.652f, 7.537f, 76.266f, 8.636f, 77.157f)
                curveTo(9.736f, 78.048f, 11.349f, 77.879f, 12.241f, 76.78f)
                lineTo(19.075f, 68.35f)
                curveTo(24.85f, 73.644f, 32.548f, 76.874f, 41f, 76.874f)
                curveTo(49.452f, 76.874f, 57.15f, 73.644f, 62.925f, 68.35f)
                lineTo(69.76f, 76.78f)
                curveTo(70.651f, 77.879f, 72.265f, 78.048f, 73.364f, 77.157f)
                curveTo(74.463f, 76.266f, 74.632f, 74.652f, 73.741f, 73.552f)
                lineTo(66.452f, 64.562f)
                close()
                moveTo(41f, 24.77f)
                curveTo(42.415f, 24.77f, 43.563f, 25.918f, 43.563f, 27.333f)
                verticalLineTo(43.045f)
                lineTo(52.671f, 49.117f)
                curveTo(53.849f, 49.902f, 54.167f, 51.493f, 53.382f, 52.671f)
                curveTo(52.597f, 53.848f, 51.006f, 54.167f, 49.829f, 53.382f)
                lineTo(39.579f, 46.548f)
                curveTo(38.866f, 46.073f, 38.438f, 45.273f, 38.438f, 44.416f)
                verticalLineTo(27.333f)
                curveTo(38.438f, 25.918f, 39.585f, 24.77f, 41f, 24.77f)
                close()
            }
        }.build()

        return _Alarm!!
    }

@Suppress("ObjectPropertyName")
private var _Alarm: ImageVector? = null
