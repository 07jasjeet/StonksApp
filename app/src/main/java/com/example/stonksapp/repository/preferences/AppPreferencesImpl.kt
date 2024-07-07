package com.example.stonksapp.repository.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.stonksapp.data.Theme
import com.example.stonksapp.repository.preferences.AppPreferencesImpl.Companion.PreferenceKeys.THEME
import com.example.stonksapp.utils.DataStoreSerializers.themeSerializer
import com.jasjeet.typesafe_datastore.preferences.ComplexPreference
import com.jasjeet.typesafe_datastore_gson.AutoTypedDataStore

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class AppPreferencesImpl(context: Context): AutoTypedDataStore(context.dataStore), AppPreferences {
    companion object {
        private object PreferenceKeys {
            val THEME = stringPreferencesKey("THEME")
        }
    }

    override val theme: ComplexPreference<Theme>
        get() = createComplexPreference(THEME, themeSerializer)
}