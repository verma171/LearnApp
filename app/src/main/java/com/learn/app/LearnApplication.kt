package com.learn.app

import android.app.Application
import com.learn.core.LearnLogger
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class LearnApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LearnLogger.init()
    }

}