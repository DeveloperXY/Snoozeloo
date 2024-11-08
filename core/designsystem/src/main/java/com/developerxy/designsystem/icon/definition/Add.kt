package com.developerxy.designsystem.icon.definition

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val Add: ImageVector
    get() {
        if (_Add != null) {
            return _Add!!
        }
        _Add = ImageVector.Builder(
            name = "Add",
            defaultWidth = 38.dp,
            defaultHeight = 38.dp,
            viewportWidth = 38f,
            viewportHeight = 38f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(20.188f, 9.5f)
                curveTo(20.188f, 8.844f, 19.656f, 8.313f, 19f, 8.313f)
                curveTo(18.344f, 8.313f, 17.813f, 8.844f, 17.813f, 9.5f)
                verticalLineTo(17.813f)
                horizontalLineTo(9.5f)
                curveTo(8.844f, 17.813f, 8.313f, 18.344f, 8.313f, 19f)
                curveTo(8.313f, 19.656f, 8.844f, 20.188f, 9.5f, 20.188f)
                horizontalLineTo(17.813f)
                verticalLineTo(28.5f)
                curveTo(17.813f, 29.156f, 18.344f, 29.688f, 19f, 29.688f)
                curveTo(19.656f, 29.688f, 20.188f, 29.156f, 20.188f, 28.5f)
                verticalLineTo(20.188f)
                horizontalLineTo(28.5f)
                curveTo(29.156f, 20.188f, 29.688f, 19.656f, 29.688f, 19f)
                curveTo(29.688f, 18.344f, 29.156f, 17.813f, 28.5f, 17.813f)
                horizontalLineTo(20.188f)
                verticalLineTo(9.5f)
                close()
            }
        }.build()

        return _Add!!
    }

@Suppress("ObjectPropertyName")
private var _Add: ImageVector? = null
