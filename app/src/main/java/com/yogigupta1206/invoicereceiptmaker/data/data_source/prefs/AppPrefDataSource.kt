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

class AppPrefDataSource {

    companion object{
        private const val USER_PREFERENCES_NAME = "preferences"
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES_NAME)

    suspend fun saveInPref(context: Context, key: String, value: Int) {
        val preferencesKey = intPreferencesKey(key)
        context.dataStore.edit { preference ->
            preference[preferencesKey] = value
        }
    }

    suspend fun saveInPref(context: Context, key: String, value: Double) {
        val preferencesKey = doublePreferencesKey(key)
        context.dataStore.edit { preference ->
            preference[preferencesKey] = value
        }
    }

    suspend fun saveInPref(context: Context, key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preference ->
            preference[preferencesKey] = value
        }
    }

    suspend fun saveInPref(context: Context, key: String, value: Boolean) {
        val preferencesKey = booleanPreferencesKey(key)
        context.dataStore.edit { preference ->
            preference[preferencesKey] = value
        }
    }

    suspend fun saveInPref(context: Context, key: String, value: Float) {
        val preferencesKey = floatPreferencesKey(key)
        context.dataStore.edit { preference ->
            preference[preferencesKey] = value
        }
    }

    suspend fun saveInPref(context: Context, key: String, value: Long) {
        val preferencesKey = longPreferencesKey(key)
        context.dataStore.edit { preference ->
            preference[preferencesKey] = value
        }
    }

    suspend fun saveInPref(context: Context, key: String, value: Set<String>) {
        val preferencesKey = stringSetPreferencesKey(key)
        context.dataStore.edit { preference ->
            preference[preferencesKey] = value
        }
    }

    suspend fun getIntFromPref(context: Context, key: String): Int {
        val preferencesKey = intPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey] ?: 0
    }

    suspend fun getDoubleFromPref(context: Context, key: String): Double {
        val preferencesKey = doublePreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey] ?: 0.0
    }

    suspend fun getStringFromPref(context: Context, key: String): String {
        val preferencesKey = stringPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey] ?: ""
    }

    suspend fun getBooleanFromPref(context: Context, key: String): Boolean {
        val preferencesKey = booleanPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey] ?: false
    }

    suspend fun getFloatFromPref(context: Context, key: String): Float {
        val preferencesKey = floatPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey] ?: 0.0f
    }

    suspend fun getLongFromPref(context: Context, key: String): Long {
        val preferencesKey = longPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey] ?: 0L
    }

    suspend fun getStringSetFromPref(context: Context, key: String): Set<String> {
        val preferencesKey = stringSetPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey] ?: emptySet()
    }

    suspend fun removeFromPref(context: Context, key: String) {
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

    suspend fun clearAllPref(context: Context) {
        context.dataStore.edit { preference ->
            preference.clear()
        }
    }

}