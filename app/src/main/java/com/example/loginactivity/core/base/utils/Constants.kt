package com.example.loginactivity.core.base.utils

class Constants {
    companion object {
        const val PERMISSION_REQUEST_CODE = 1001
        const val BASE_URL = "https://portal.bvdpetroleum.com:82/"
        const val LOCAL_DB_NAME = "bvd_database.db"
        const val SHARED_PREF_NAME = "bvd_shared_pref"
        const val KEY_AUTH_TOKEN = "auth_token"
        const val IS_LOGGED_IN = "is_logged_in"
        const val NUM_PAGES = 3

        val permissionsToCheck = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_WIFI_STATE
        )
    }
}