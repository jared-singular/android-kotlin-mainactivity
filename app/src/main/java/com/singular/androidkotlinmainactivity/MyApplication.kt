package com.singular.androidkotlinmainactivity

import android.app.Application
import android.util.Log

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("Singular", "--Application-- Lifecycle onCreate")
        // Perform any application-wide initialization here
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d("Singular", "Application Lifecycle onTerminate")
        // Perform any application-wide terminations here
    }
}
