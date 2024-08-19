package jp.co.soramitsu.androidfoundation.compose

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.debugInspectorInfo

@Suppress("FunctionName")
@SuppressLint("ModifierFactoryExtensionFunction")
inline fun SwitchTo(modifier: Modifier, inCase: () -> Boolean) =
    if (inCase()) modifier else Modifier

const val ALWAYS: Boolean = true

@Suppress("NOTHING_TO_INLINE")
inline fun Modifier.chain(vararg modifiers: Modifier): Modifier =
    modifiers.fold(this) { acc, modifier -> acc then modifier }

fun Modifier.shake(enabled: Boolean, onAnimationEnd: () -> Unit = {}) = composed(
    factory = {
        val scale by animateFloatAsState(
            targetValue = if (enabled) 0f else 20f,
            animationSpec = repeatable(
                iterations = 5,
                animation = tween(durationMillis = 50, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            finishedListener = { onAnimationEnd() }, label = ""
        )

        Modifier.graphicsLayer {
            translationX = if (enabled) scale else 0f
        }
    },
    inspectorInfo = debugInspectorInfo {
        name = "shake"
        properties["enabled"] = enabled
    }
)