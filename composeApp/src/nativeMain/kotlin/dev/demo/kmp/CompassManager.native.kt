package dev.demo.kmp

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import platform.CoreLocation.CLHeading
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.darwin.NSObject

actual class CompassManager  : NSObject(), CLLocationManagerDelegateProtocol {
    private val locationManager = CLLocationManager()
    private val _heading = MutableStateFlow(0f)
    actual   val heading: StateFlow<Float> = _heading

    actual  fun start() {
        locationManager.startUpdatingHeading()
        locationManager.delegate = this
    }

    actual fun stop() {
        locationManager.stopUpdatingHeading()
    }


    override fun locationManager(manager: CLLocationManager, didUpdateHeading: CLHeading) {
        _heading.value = didUpdateHeading.trueHeading.toFloat()
    }
}