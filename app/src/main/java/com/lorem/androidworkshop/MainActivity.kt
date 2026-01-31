package com.lorem.androidworkshop

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.lorem.androidworkshop.ui.theme.AndroidWorkShopTheme
import com.lorem.androidworkshop.workmanagers.MyNotificationWorker
import com.lorem.androidworkshop.workmanagers.WelcomeWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    private val NOTIFICATION_PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestNotificationPermission()

        enableEdgeToEdge()
        setContent {
            AndroidWorkShopTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_CODE
                )
            } else {
                generatePeriodicWorkManager(this)

                generateOneTimeWorkManager(this)
            }
        } else {
            // Android 12 and below
            generatePeriodicWorkManager(this)

            generateOneTimeWorkManager(this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)

        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                generatePeriodicWorkManager(this)

                generateOneTimeWorkManager(this)
            } else {
                Log.d("Permission", "Notification permission denied")
                // Optional: show snackbar or fallback UI
            }
        }
    }
}


private fun generateOneTimeWorkManager(context: Context) {
    val welcomeWorkRequest = OneTimeWorkRequestBuilder<WelcomeWorker>().setConstraints(
        Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    ).setInitialDelay(15, TimeUnit.SECONDS).build()

    WorkManager.getInstance(context).enqueueUniqueWork("network_sync_work",
        ExistingWorkPolicy.KEEP,welcomeWorkRequest)
}

private fun generatePeriodicWorkManager(context: Context) {
    val hydrationWorkRequest = PeriodicWorkRequestBuilder<MyNotificationWorker>(
        1, TimeUnit.HOURS // Minimum interval is 15 minutes
    ).setConstraints(
        Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()
    ).build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "HydrationWork",
        ExistingPeriodicWorkPolicy.UPDATE,
        hydrationWorkRequest
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidWorkShopTheme {
        Greeting("Android")
    }
}
