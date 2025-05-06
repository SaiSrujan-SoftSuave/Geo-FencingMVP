package dev.demo.kmp

@Composable
fun MainScreen(viewModel: GeofenceViewModel) {
    val heading by viewModel.currentHeading.collectAsState()
    val showAlert by viewModel.isAlertShown.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        // Compass Display
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = center
            drawCircle(color = Color.Blue, radius = 50f)
            drawLine(
                color = Color.Red,
                start = center,
                end = Offset(
                    center.x + cos(heading.toDouble()) * 100f,
                    center.y + sin(heading.toDouble()) * 100f
                ),
                strokeWidth = 4f
            )
        }

        // Geofence Controls
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Add your existing radius controls here
            Button(onClick = { /* Start/stop logic */ }) {
                Text("Toggle Geofencing")
            }
        }

        if (showAlert) {
            AlertDialog(
                onDismissRequest = { viewModel.dismissAlert() },
                title = { Text("Geofence Alert") },
                text = { Text("You've exited the geofenced area!") },
                confirmButton = {
                    Button(onClick = { viewModel.dismissAlert() }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}