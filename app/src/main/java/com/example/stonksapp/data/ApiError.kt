package com.example.stonksapp.data

import com.google.gson.annotations.SerializedName

data class ApiError (
    @SerializedName("Error Message")
    val error: String? = null
)