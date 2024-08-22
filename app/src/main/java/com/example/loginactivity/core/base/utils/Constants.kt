package com.example.loginactivity.core.base.utils

class Constants {
    companion object {
        const val PERMISSION_REQUEST_CODE = 1001
        const val BASE_URL = "https://portal.bvdpetroleum.com:82/"
        const val BASE_URL_LOCAL = "http://bvdpro.local.bvdpetro.com:82"
        const val PUMP_BASE_URL = "http://192.168.0.180:8080"
        const val PUMP_START_CMD = "PMPON"
        const val PUMP_STOP_CMD = "PMPOFF"
        const val LOCAL_DB_NAME = "bvd_database.db"
        const val SHARED_PREF_NAME = "bvd_shared_pref"
        const val KEY_AUTH_TOKEN = "auth_token"
        const val IS_LOGGED_IN = "is_logged_in"
        const val IS_BOARDING_SHOWN = "is_boarding_shown"
        const val DRIVER_ID = "driver_id"
        const val TRUCK_ID = "truck_id"
        const val SWITCH_STATE = "Switch_state"


        const val NUM_PAGES = 3
        const val VIN_NUMBER_LENGTH = 3
        val permissionsToCheck = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_WIFI_STATE
        )
    }
}