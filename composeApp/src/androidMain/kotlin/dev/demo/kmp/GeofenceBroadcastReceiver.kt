package dev.demo.kmp

// GeofenceBroadcastReceiver.kt
class GeofenceBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        geofencingEvent?.let {
            if (it.hasError()) {
                Log.e("Geofence", "Geofence error: ${it.errorCode}")
                return
            }

            when (it.geofenceTransition) {
                Geofence.GEOFENCE_TRANSITION_EXIT -> {
                    // Notify shared code
                    GeofenceViewModel().triggerGeofenceAlert()
                }
            }
        }
    }
}