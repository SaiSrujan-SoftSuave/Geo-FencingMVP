package dev.demo.kmp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent

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