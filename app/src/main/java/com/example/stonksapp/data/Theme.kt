package com.example.stonksapp.data

enum class Theme {
    DARK,
    LIGHT,
    FOLLOW_SYSTEM;

    companion object {
        fun String?.asTheme(): Theme =
            when (this) {
                LIGHT.name -> LIGHT
                DARK.name -> DARK
                else -> FOLLOW_SYSTEM
            }
    }
}