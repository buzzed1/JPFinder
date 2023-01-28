package com.buzzed.jpfinder.data

import android.content.Context

interface AppContainer {
    val jpRepository: JPRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val jpRepository: JPRepository by lazy {
        OfflineJPRepository(JPFinderDatabase.getDatabase(context).JPDao())
    }
}