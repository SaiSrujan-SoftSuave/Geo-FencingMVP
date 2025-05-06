package dev.demo.kmp

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.github.jordond.compass.Compass

actual class CompassManager constructor(
    private val context: Context
) {
    private val compass = Compass(context)
    private val _heading = MutableStateFlow(0f)
    actual   val heading: StateFlow<Float> = _heading

    actual  fun start() {
        compass.start { heading, _ -> _heading.value = heading }
    }

    actual fun stop() {
        compass.stop()
    }
}