package com.example.stonksapp.repository.preferences

import com.example.stonksapp.data.Theme
import com.jasjeet.typesafe_datastore.preferences.ComplexPreference

interface AppPreferences {
    val theme: ComplexPreference<Theme>
}