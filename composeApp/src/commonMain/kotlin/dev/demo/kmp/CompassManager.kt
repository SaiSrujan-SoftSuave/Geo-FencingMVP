package dev.demo.kmp
import kotlinx.coroutines.flow.StateFlow

expect class CompassManager {
    fun start()
    fun stop()
    val heading: StateFlow<Float>
}