package com.example.loginactivity.core.base.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.Uri
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference


object AppUtils {
    private lateinit var connectivityManager: ConnectivityManager
    private var activeNetwork: Network? = null

    private lateinit var applicationContext: Context

    private val coroutineScope = CoroutineScope(Dispatchers.Default)


    fun init(application: Application) {
        applicationContext = application.applicationContext
        connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        activeNetwork = connectivityManager.activeNetwork


    }

    //permission handlers
    fun checkAndRequestPermissions(activity: Activity): MutableList<String> {
        val permissionsToRequest = mutableListOf<String>()

        for (permission in Constants.permissionsToCheck) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionsToRequest.add(permission)
            }
        }
        return permissionsToRequest
    }


    fun getColor(colorResId: Int): Int {
        return ContextCompat.getColor(applicationContext, colorResId)
    }

    fun getString(stringResId: Int): String {
        return applicationContext.getString(stringResId)
    }

    fun getString( stringResId: Int, vararg formatArgs: Any): String {
        return applicationContext.getString(stringResId, *formatArgs)
    }

    fun setBackgroundColor(view: View, colorResId: Int) {
        view.setBackgroundColor(getColor(colorResId))
    }

    fun redirectToAppSettings(context: Context) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            val uri = Uri.fromParts("package", context.packageName, null)
            data = uri
        }
        context.startActivity(intent)
    }

    //utility functions
    fun showLog(tag:String,keyTerm : String, message: String){
        Log.d("$tag Key: $keyTerm : ", message)

    }
    fun showConfirmationDialog(
        context: Context,
        title: String,
        message: String,
        positiveButtonText: String,
        negativeButtonText: String,
        positiveAction: () -> Unit,
        negativeAction: () -> Unit
    ) {
        val weakContext = WeakReference(context)
        val safeContext = weakContext.get()

        safeContext?.let {
            AlertDialog.Builder(it)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText) { dialog, _ ->
                    positiveAction()
                    dialog.dismiss()
                }
                .setNegativeButton(negativeButtonText) { dialog, _ ->
                    negativeAction()
                    dialog.dismiss()
                }
                .show()
        }
    }

    fun showToastMessage(message: String) {
        coroutineScope.launch(Dispatchers.Main) {
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun hideSystemUI(activity: Activity) {
        // Hides the action bar at the top
        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        // Hide the status bars and navigation bar
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            // For versions below Android R
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            activity.window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                            View.SYSTEM_UI_FLAG_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    )
        } else {
            // For Android R and above
            activity.window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }

    //wifi and network
    private fun getActiveNetwork(): Boolean {
        return activeNetwork != null
    }

    fun isConnectedToSpecificWifi(context: Context, desiredSSID: String): Boolean {

        if (getActiveNetwork()) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            if (networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val wifiInfo =
                    if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.S) {
                        networkCapabilities.transportInfo as WifiInfo
                    } else {
                        wifiManager.connectionInfo
                    }

                val ssid = wifiInfo.ssid
                val currentSSID =
                    if (ssid != null && ssid.startsWith("\"") && ssid.endsWith("\"")) {
                        ssid.substring(1, ssid.length - 1)
                    } else {
                        ssid
                    }
                Log.d("Current SSID", "" + currentSSID)
                return currentSSID == desiredSSID
            }
            return false
        }
        return false
    }

    fun ComponentActivity.hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11 and above
            window.insetsController?.let { controller ->
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            // Android 9 to Android 10
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                    )
            @Suppress("DEPRECATION")
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            @Suppress("DEPRECATION")
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
    }

    fun ComponentActivity.showSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11 and above
            window.insetsController?.let { controller ->
                controller.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            // Android 9 to Android 10
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
            @Suppress("DEPRECATION")
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            @Suppress("DEPRECATION")
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
    }
}