package com.tpanh.jobfinder

import android.app.Application
import com.tpanh.jobfinder.di.AppContainer
import com.tpanh.jobfinder.di.DefaultAppContainer

class JobFinderApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}