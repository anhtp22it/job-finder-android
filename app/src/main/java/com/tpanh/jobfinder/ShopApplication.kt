package com.tpanh.jobfinder

import android.app.Application
import com.tpanh.jobfinder.data.container.AppContainer
import com.tpanh.jobfinder.data.container.DefaultAppContainer

class ShopApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }

}