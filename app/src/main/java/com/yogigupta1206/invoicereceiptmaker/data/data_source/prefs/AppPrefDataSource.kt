package com.yogigupta1206.invoicereceiptmaker.data.data_source.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AppPrefDataSource @Inject constructor(private val context: Context){

    companion object{
        private const val USER_PREFERENCES_NAME = "preferences"
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES_NAME)

    suspend fun saveInPref(key: String, value: Int) {
        val preferencesKey = intPreferencesKey(key)
        context.dataStore.edit { preference ->
            preference[preferencesKey] = value
        }
    }

    suspend fun saveInPref(key: String, value: Double) {
        val preferencesKey = doublePreferencesKey(key)
        context.dataStore.edit { preference ->
            preference[preferencesKey] = value
        }
    }

    suspend fun saveInPref(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preference ->
            preference[preferencesKey] = value
        }
    }

    suspend fun saveInPref(key: String, value: Boolean) {
        val preferencesKey = booleanPreferencesKey(key)
        context.dataStore.edit { preference ->
            preference[preferencesKey] = value
        }
    }

    suspend fun saveInPref(key: String, value: Float) {
        val preferencesKey = floatPreferencesKey(key)
        context.dataStore.edit { preference ->
            preference[preferencesKey] = value
        }
    }

    suspend fun saveInPref(key: String, value: Long) {
        val preferencesKey = longPreferencesKey(key)
        context.dataStore.edit { preference ->
            preference[preferencesKey] = value
        }
    }

    suspend fun saveInPref(key: String, value: Set<String>) {
        val preferencesKey = stringSetPreferencesKey(key)
        context.dataStore.edit { preference ->
            preference[preferencesKey] = value
        }
    }

    suspend fun getIntFromPref(key: String): Int {
        val preferencesKey = intPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey] ?: 0
    }

    suspend fun getDoubleFromPref(key: String): Double {
        val preferencesKey = doublePreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey] ?: 0.0
    }

    suspend fun getStringFromPref(key: String): String {
        val preferencesKey = stringPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey] ?: ""
    }

    suspend fun getBooleanFromPref(key: String): Boolean {
        val preferencesKey = booleanPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey] ?: false
    }

    suspend fun getFloatFromPref(key: String): Float {
        val preferencesKey = floatPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey] ?: 0.0f
    }

    suspend fun getLongFromPref(key: String): Long {
        val preferencesKey = longPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey] ?: 0L
    }

    suspend fun getStringSetFromPref(key: String): Set<String> {
        val preferencesKey = stringSetPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey] ?: emptySet()
    }

    suspend fun removeFromPref(key: String) {
        context.dataStore.edit { preference ->
            preference.remove(intPreferencesKey(key))
            preference.remove(doublePreferencesKey(key))
            preference.remove(stringPreferencesKey(key))
            preference.remove(booleanPreferencesKey(key))
            preference.remove(floatPreferencesKey(key))
            preference.remove(longPreferencesKey(key))
            preference.remove(stringSetPreferencesKey(key))
        }
    }

    suspend fun clearAllPref() {
        context.dataStore.edit { preference ->
            preference.clear()
        }
    }

}