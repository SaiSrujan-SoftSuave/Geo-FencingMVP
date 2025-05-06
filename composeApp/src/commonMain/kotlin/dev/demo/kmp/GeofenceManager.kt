package dev.demo.kmp

expect class GeofenceManager {
    fun startGeofencing(latitude: Double, longitude: Double, radius: Float)
    fun stopGeofencing()
    fun isGeofenceSupported(): Boolean
}