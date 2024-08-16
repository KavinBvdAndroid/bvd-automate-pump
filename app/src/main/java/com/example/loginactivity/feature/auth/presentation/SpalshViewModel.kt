package com.example.loginactivity.feature.auth.presentation

import androidx.lifecycle.ViewModel
import com.example.bvddriverfleetapp.data.sharedpref.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val sessionManager: SessionManager) : ViewModel() {

    fun isUserLoggedIn() : Boolean{
        return sessionManager.getLoggedIn()
    }

}