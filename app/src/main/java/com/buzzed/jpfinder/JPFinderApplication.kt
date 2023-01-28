package com.buzzed.jpfinder

import android.app.Application
import com.buzzed.jpfinder.data.AppContainer
import com.buzzed.jpfinder.data.AppDataContainer

class JPFinderApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}