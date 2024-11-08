package com.developerxy.designsystem.icon.definition

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val Close: ImageVector
    get() {
        if (_Close != null) {
            return _Close!!
        }
        _Close = ImageVector.Builder(
            name = "Close",
            defaultWidth = 32.dp,
            defaultHeight = 32.dp,
            viewportWidth = 32f,
            viewportHeight = 32f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFFE6E6E6)),
                    pathFillType = PathFillType.EvenOdd
                ) {
                    moveTo(6.4f, 0f)
                    horizontalLineTo(25.6f)
                    curveTo(29.135f, 0f, 32f, 2.865f, 32f, 6.4f)
                    verticalLineTo(25.6f)
                    curveTo(32f, 29.135f, 29.135f, 32f, 25.6f, 32f)
                    horizontalLineTo(6.4f)
                    curveTo(2.866f, 32f, 0f, 29.135f, 0f, 25.6f)
                    verticalLineTo(6.4f)
                    curveTo(0f, 2.865f, 2.866f, 0f, 6.4f, 0f)
                    close()
                    moveTo(21.374f, 10.626f)
                    curveTo(21.843f, 11.095f, 21.843f, 11.854f, 21.374f, 12.323f)
                    lineTo(17.697f, 16f)
                    lineTo(21.374f, 19.677f)
                    curveTo(21.843f, 20.146f, 21.843f, 20.905f, 21.374f, 21.374f)
                    curveTo(20.906f, 21.843f, 20.146f, 21.843f, 19.677f, 21.374f)
                    lineTo(16f, 17.697f)
                    lineTo(12.323f, 21.374f)
                    curveTo(11.855f, 21.843f, 11.095f, 21.843f, 10.626f, 21.374f)
                    curveTo(10.158f, 20.906f, 10.158f, 20.146f, 10.626f, 19.677f)
                    lineTo(14.303f, 16f)
                    lineTo(10.626f, 12.323f)
                    curveTo(10.158f, 11.854f, 10.158f, 11.094f, 10.626f, 10.626f)
                    curveTo(11.095f, 10.157f, 11.855f, 10.157f, 12.323f, 10.626f)
                    lineTo(16f, 14.303f)
                    lineTo(19.677f, 10.626f)
                    curveTo(20.146f, 10.157f, 20.906f, 10.157f, 21.374f, 10.626f)
                    close()
                }
            }
        }.build()

        return _Close!!
    }

@Suppress("ObjectPropertyName")
private var _Close: ImageVector? = null
