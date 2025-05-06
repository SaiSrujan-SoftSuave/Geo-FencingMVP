package dev.demo.kmp
import platform.CoreLocation.CLCircularRegion
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.darwin.NSObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


actual class GeofenceManager {

        private val locationManager = CLLocationManager()

        actual  fun startGeofencing(latitude: Double, longitude: Double, radius: Float) {
            // iOS geofencing implementation
        }

        actual fun stopGeofencing() {
            // Stop geofencing
        }

        actual   fun isGeofenceSupported(): Boolean {
            return CLLocationManager.isMonitoringAvailableForClass(CLCircularRegion::class.java)
        }

}