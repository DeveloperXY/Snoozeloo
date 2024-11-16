import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.developerxy.designsystem.theme.SnoozelooPrimaryBlue
import com.developerxy.designsystem.theme.SnoozelooSoftBackgroundBlue

@Composable
fun SnoozelooSlider(
    modifier: Modifier = Modifier,
    sliderHeight: Float = 12f, // Height of the slider bar
    thumbRadius: Float = 8f, // Radius of the thumb
    initialValue: Float = 0f, // Initial slider value
    range: ClosedFloatingPointRange<Float> = 0f..1f, // Value range
    onValueChange: (Float) -> Unit // Callback for value changes
) {
    var sliderValue by remember { mutableStateOf(initialValue) }
    var sliderWidthPx by remember { mutableStateOf(1f) } // Slider width in pixels

    // Smooth animation for the slider progress and thumb position
    val animatedValue by animateFloatAsState(targetValue = sliderValue)

    Box(
        modifier = modifier
            .height((thumbRadius * 2).dp)
            .onSizeChanged { size ->
                sliderWidthPx = size.width.toFloat() // Get the width of the slider
            }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    change.consume()
                    val newValue = (sliderValue + dragAmount / sliderWidthPx).coerceIn(range)
                    sliderValue = newValue
                    onValueChange(sliderValue)
                }
            }
    ) {
        // Draw slider bar
        Canvas(modifier = Modifier.matchParentSize()) {
            // Background bar
            drawRoundRect(
                color = SnoozelooSoftBackgroundBlue,
                topLeft = Offset(0f, (size.height - sliderHeight) / 2),
                size = Size(size.width, sliderHeight),
                cornerRadius = CornerRadius(sliderHeight / 2, sliderHeight / 2)
            )

            // Progress bar
            drawRoundRect(
                color = SnoozelooPrimaryBlue,
                topLeft = Offset(0f, (size.height - sliderHeight) / 2),
                size = Size(animatedValue * size.width, sliderHeight),
                cornerRadius = CornerRadius(sliderHeight / 2, sliderHeight / 2)
            )
        }

        // Draw the thumb
        Box(
            modifier = Modifier
                .offset(x = (animatedValue * sliderWidthPx).asPxToDp() - thumbRadius.dp)
                .size((thumbRadius * 2).dp)
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary
            ) {}
        }
    }
}

@Composable
private fun Float.asPxToDp(): Dp {
    val density = LocalDensity.current
    return with(density) { toDp() }
}