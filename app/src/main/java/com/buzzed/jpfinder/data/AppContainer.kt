package com.buzzed.jpfinder.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

interface AppContainer {
    val jpRepository: JPRepository

}




class AppDataContainer(private val context: Context,) : AppContainer {


    override val jpRepository: JPRepository by lazy {
        OfflineJPRepository(JPFinderDatabase.getDatabase(context).JPDao())
    }

}