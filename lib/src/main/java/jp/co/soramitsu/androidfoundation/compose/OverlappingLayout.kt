package jp.co.soramitsu.androidfoundation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Stable
enum class OverlappingAxis {
    Vertical,
    Horizontal,
    Diagonal,
}

@Composable
inline fun OverlappingLayout(
    modifier: Modifier,
    overlappingFactor: Float,
    overlappingAxis: OverlappingAxis,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map { it.measure(constraints) }

            val width =
                if (overlappingAxis !== OverlappingAxis.Vertical) {
                    val lastWidth = placeables.lastOrNull()?.width ?: 0
                    val widthsExceptLast = placeables.dropLast(1).sumOf { it.width }

                    (widthsExceptLast * overlappingFactor + lastWidth).toInt()
                } else {
                    placeables.maxOf { it.width }
                }

            val height =
                if (overlappingAxis !== OverlappingAxis.Horizontal) {
                    val lastHeight = placeables.lastOrNull()?.height ?: 0
                    val heightsExceptLast = placeables.dropLast(1).sumOf { it.height }

                    (heightsExceptLast * overlappingFactor + lastHeight).toInt()
                } else {
                    placeables.maxOf { it.height }
                }

            layout(width, height) {
                var y = 0
                var x = 0
                var zIndex = 0f

                for (placeable in placeables) {
                    placeable.placeRelative(x, y, zIndex)
                    x +=
                        if (overlappingAxis !== OverlappingAxis.Vertical) {
                            (placeable.width * overlappingFactor).toInt()
                        } else {
                            0
                        }

                    y +=
                        if (overlappingAxis !== OverlappingAxis.Horizontal) {
                            (placeable.height * overlappingFactor).toInt()
                        } else {
                            0
                        }

                    zIndex++
                }
            }
        },
    )
}
