package com.buzzed.jpfinder

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.buzzed.jpfinder.data.AppContainer
import com.buzzed.jpfinder.data.AppDataContainer
import com.buzzed.jpfinder.data.UserPreferencesRepository

private const val IS_FAVORITED_PREFERENCES_NAME = "isfavorited_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = IS_FAVORITED_PREFERENCES_NAME
)
class JPFinderApplication : Application() {

    lateinit var userPreferencesRepository: UserPreferencesRepository
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        userPreferencesRepository = UserPreferencesRepository(dataStore)
        container = AppDataContainer(this,)
    }
}