package com.buzzed.jpfinder.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException



class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
)  {

     companion object {
      private val IS_FAVORITED = booleanPreferencesKey("isFavorited")
      private const val TAG = "UserPreferencesRepo"
    }

    val isFavorited: Flow<Boolean> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading from preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map {preferences ->
            preferences[IS_FAVORITED] ?: false
        }

    suspend fun saveFavorited(isFavorited: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_FAVORITED] = isFavorited

        }

    }

}