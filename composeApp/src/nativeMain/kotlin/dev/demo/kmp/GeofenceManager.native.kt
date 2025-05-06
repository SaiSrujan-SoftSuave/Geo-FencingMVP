package dev.demo.kmp

actual class GeofenceManager : NSObject(), CLLocationManagerDelegateProtocol {
    private val locationManager = CLLocationManager()
    private var currentRegion: CLCircularRegion? = null

    actual override fun startGeofencing(latitude: Double, longitude: Double, radius: Float) {
        locationManager.requestWhenInUseAuthorization()

        val center = CLLocationCoordinate2DMake(latitude, longitude)
        val region = CLCircularRegion(
            center = center,
            radius = radius.toDouble(),
            identifier = "kmp_geofence"
        ).apply {
            notifyOnExit = true
        }

        locationManager.startMonitoringForRegion(region)
        currentRegion = region
    }

    actual override fun stopGeofencing() {
        currentRegion?.let { locationManager.stopMonitoringForRegion(it) }
    }

    actual override fun isGeofenceSupported(): Boolean = true

    @Override
    override fun locationManager(manager: CLLocationManager, didExitRegion region: CLRegion) {
        GeofenceViewModel().triggerGeofenceAlert()
    }
}