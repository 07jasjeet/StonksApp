package com.example.stonksapp.utils

import com.example.stonksapp.data.Theme
import com.example.stonksapp.data.Theme.Companion.asTheme
import com.jasjeet.typesafe_datastore.DataStoreSerializer

object DataStoreSerializers {
    val themeSerializer: DataStoreSerializer<Theme, String>
        get() = object: DataStoreSerializer<Theme, String> {
            override fun from(value: String): Theme =
                value.asTheme()
        
            override fun to(value: Theme): String =
                value.name
        
            override fun default(): Theme = Theme.FOLLOW_SYSTEM
        }
}