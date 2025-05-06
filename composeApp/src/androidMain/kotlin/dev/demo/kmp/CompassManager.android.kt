package dev.demo.kmp

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


// AndroidCompassManager.kt
actual class AndroidCompassManager(context: Context) : CompassManager {
    private val compass = Compass(context)
    private val _heading = MutableStateFlow(0f)

    override val heading: StateFlow<Float> = _heading

    actual override fun start() {
        compass.start(object : Compass.HeadingListener {
            override fun onHeadingChanged(heading: Float, accuracy: Int) {
                _heading.value = heading
            }
        })
    }

    actual override fun stop() {
        compass.stop()
    }
}