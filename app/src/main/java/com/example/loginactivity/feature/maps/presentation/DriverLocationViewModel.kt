package com.example.loginactivity.feature.maps.presentation

import androidx.lifecycle.ViewModel
import com.example.bvddriverfleetapp.data.sharedpref.SessionManager
import com.example.bvddriverfleetapp.data.sharedpref.SharedPrefMethods
import com.example.loginactivity.data.sharedpref.LoggingSharedPrefMethods
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DriverLocationViewModel @Inject constructor(private val sessionManager: SessionManager) : ViewModel(),
    LoggingSharedPrefMethods {

    override fun clearSharedPref():Boolean {
      return sessionManager.clearSharedPref()
    }


}