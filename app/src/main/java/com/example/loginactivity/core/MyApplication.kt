package com.example.loginactivity.core

import android.app.Application
import com.example.loginactivity.core.base.utils.AppUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppUtils.init(this)

    }

    override fun onTerminate() {
        super.onTerminate()
    }
}
