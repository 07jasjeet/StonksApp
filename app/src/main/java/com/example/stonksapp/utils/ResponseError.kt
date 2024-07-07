package com.example.stonksapp.utils

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Stable
import com.example.stonksapp.data.ApiError
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.io.FileNotFoundException
import java.io.IOException
import java.net.SocketTimeoutException

/** These exceptions need to be handled in view-model, shown via UI and not just thrown.
 * @param genericToast Generic message for the error.
 * @param actualResponse Actual response given by the server.*/
@Stable
enum class ResponseError(val genericToast: String, var actualResponse: String? = null) {
    
    DOES_NOT_EXIST(genericToast = "Error! Object not found."),     // "User Some_User_That_Does_Not_Exist not found"
    
    BAD_REQUEST(genericToast = "Illegal action."),     // "Jasjeet is already following user someotheruser", "Whoops, cannot follow yourself."
    
    /** Also means the user is not logged in.*/
    AUTH_HEADER_NOT_FOUND(genericToast = "Please login in order to perform this operation."),    // "You need to provide an Authorization header.
    
    RATE_LIMIT_EXCEEDED(genericToast = "Rate limit exceeded."),
    
    UNAUTHORISED(genericToast = "You are not authorised to access the requested content."),
    
    INTERNAL_SERVER_ERROR(genericToast = "Internal server error."),
    
    BAD_GATEWAY(genericToast = "Error! Bad gateway."),
    
    SERVICE_UNAVAILABLE(genericToast = "Server outage detected. Please try again later."),
    
    NETWORK_ERROR(genericToast = "Network issues detected. Make sure device is connected to internet."),
    
    FILE_NOT_FOUND(genericToast = "Selected file does not exist."),
    
    UNKNOWN(genericToast = "Some error has occurred.");
    
    
    /** Simple function that returns the most suitable message to show the user.*/
    val message: String get() = actualResponse ?: genericToast
    
    /** Wrap this [ResponseError] in [Resource] with an optional parameter to add the actual response.*/
    fun <T> asResource(actualResponse: String? = null): Resource<T> =
        Resource.failure(error = this.setActualResponse(actualResponse))
    
    fun setActualResponse(actualResponse: String?): ResponseError {
        this.apply { this@ResponseError.actualResponse = actualResponse }
        return this
    }

    override fun toString(): String {
        return "ResponseError(name=$name, actualResponse=$actualResponse)"
    }
    
    companion object {
        
        /** Get [ResponseError] for a given Retrofit **error** [Response] from server.*/
        fun <T> getError(response: Response<T>) : ResponseError {
            val code = response.code()
            val apiError = parseError(response)
            return when (code) {
                400 -> BAD_REQUEST
                401 -> AUTH_HEADER_NOT_FOUND
                403 -> UNAUTHORISED
                404 -> DOES_NOT_EXIST
                429 -> RATE_LIMIT_EXCEEDED
                500 -> INTERNAL_SERVER_ERROR
                502 -> BAD_GATEWAY
                503 -> SERVICE_UNAVAILABLE
                else -> UNKNOWN
            }.apply { actualResponse = apiError.error }
        }
        
        fun Throwable.toResponseError(): ResponseError {
            return when (this) {
                is FileNotFoundException -> FILE_NOT_FOUND
                is SocketTimeoutException -> NETWORK_ERROR
                is IOException -> NETWORK_ERROR
                else -> UNKNOWN
            }.setActualResponse(this.message)
        }
        
        /** Parsing server response into [ApiError] class. Consider using specific functions like [getSocialResponseError], etc. for each repository if
         * returning errors is the sole motive.*/
        @VisibleForTesting
        fun <T> parseError(response: Response<T>) : ApiError =
            Gson().fromJson(
                /* json = */ response.error(),
                /* typeOfT = */ object : TypeToken<ApiError>() {}.type
            )
        
    }
}