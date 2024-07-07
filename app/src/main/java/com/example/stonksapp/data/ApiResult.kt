package com.example.stonksapp.data

import com.google.gson.annotations.SerializedName

/** Due to poor API design of Alpha Vantage API, the errors in an API request are also returned as 200. This means we need to
 * include these error fields in the response.*/
abstract class AlphaVantageResponse {
    @get:SerializedName(ERROR_MESSAGE)
    abstract val errorMessage: String?

    @get:SerializedName(INFORMATION)
    abstract val information: String?

    fun hasError() = errorMessage != null || information != null
    val error get() = errorMessage ?: information

    companion object {
        const val ERROR_MESSAGE = "Error Message"
        const val INFORMATION = "Information"
    }
}