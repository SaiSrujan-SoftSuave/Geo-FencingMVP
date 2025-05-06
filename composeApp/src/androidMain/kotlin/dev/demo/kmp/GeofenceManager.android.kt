import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import dev.demo.kmp.GeofenceBroadcastReceiver

actual class AndroidGeofenceManager(context: Context) : GeofenceManager {
    private val geofencingClient = LocationServices.getGeofencingClient(context)
    private val geofencePendingIntent by lazy {
        val intent = Intent(context, GeofenceBroadcastReceiver::class.java)
        PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    actual override fun startGeofencing(latitude: Double, longitude: Double, radius: Float) {
        val geofence = Geofence.Builder()
            .setRequestId("kmp_geofence")
            .setCircularRegion(latitude, longitude, radius)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_EXIT)
            .build()

        val request = GeofencingRequest.Builder()
            .addGeofence(geofence)
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_EXIT)
            .build()

        geofencingClient.addGeofences(request, geofencePendingIntent)?.run {
            addOnSuccessListener { Log.d("Geofence", "Geofence added") }
            addOnFailureListener { Log.e("Geofence", "Error adding geofence", it) }
        }
    }

    actual override fun stopGeofencing() {
        geofencingClient.removeGeofences(geofencePendingIntent)
    }

    actual override fun isGeofenceSupported(): Boolean {
        return LocationServices.getGeofencingClient(context).locationAvailability != null
    }
}