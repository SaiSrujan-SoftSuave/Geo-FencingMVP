package dev.demo.kmp

import androidx.lifecycle.ViewModel

class GeofenceViewModel : ViewModel() {
    private val geofenceManager = GeofenceManager()
    private val compassManager = CompassManager()

    private val _isAlertShown = mutableStateFlow(false)
    private val _currentHeading = mutableStateFlow(0f)

    val isAlertShown: StateFlow<Boolean> = _isAlertShown
    val currentHeading: StateFlow<Float> = _currentHeading

    fun startMonitoring(latitude: Double, longitude: Double, radius: Float) {
        geofenceManager.startGeofencing(latitude, longitude, radius)
        compassManager.start()

        viewModelScope.launch {
            compassManager.heading.collect { heading ->
                _currentHeading.value = heading
            }
        }
    }

    fun stopMonitoring() {
        geofenceManager.stopGeofencing()
        compassManager.stop()
        _isAlertShown.value = false
    }

    fun triggerGeofenceAlert() {
        _isAlertShown.value = true
    }

    fun dismissAlert() {
        _isAlertShown.value = false
    }
}