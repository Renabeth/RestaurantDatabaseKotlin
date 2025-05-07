package com.example.homework4restaurant

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.homework4restaurant.MyPreferences.PreferenceKeys.showRatings

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MyPreferences (val context: Context) {
    object PreferenceKeys {
        val showRatings: Preferences.Key<Boolean> = booleanPreferencesKey("showRatings")
    }

    suspend fun updateShowRatings(newValue: Boolean) =
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.showRatings] = newValue
        }

    fun watchShowRatings(): Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.showRatings] ?: true // default
        }

}