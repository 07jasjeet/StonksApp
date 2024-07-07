package com.example.stonksapp.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import com.example.stonksapp.data.AlphaVantageResponse
import com.example.stonksapp.di.IoDispatcher
import com.example.stonksapp.utils.ResponseError.Companion.getError
import com.example.stonksapp.utils.ResponseError.Companion.toResponseError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/** General function to parse an API endpoint's response.
 * @param request Call the API endpoint here. Run any pre-conditional checks to directly return error/success in some cases. */
suspend inline fun <T: AlphaVantageResponse> parseResponse(
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline request: suspend () -> Response<T>
): Resource<T> = withContext(ioDispatcher) {
    runCatching {
        val response = request()

        return@runCatching if (response.isSuccessful) {
            // Specific condition of AlphaVantage API.
            val alphaVantageResponse = response.body()
            return@runCatching if (alphaVantageResponse?.hasError() == true) {
                val error = ResponseError.BAD_REQUEST.setActualResponse(alphaVantageResponse.error)
                Resource.failure(error = error)
            } else {
                Resource.success(response.body()!!)
            }
        } else {
            val error = getError(response = response)
            Resource.failure(error = error)
        }

    }.getOrElse { logAndReturn(it) }
}


fun <T> logAndReturn(it: Throwable) : Resource<T> {
    it.printStackTrace()
    return it.toResponseError().asResource()
}

/** Get human readable error.
 *
 * **CAUTION:** If this function is called once, calling it further with the same [Response] instance will result in an empty
 * string. Store this function's result for multiple use cases.*/
fun <T> Response<T>.error(): String? = this.errorBody()?.string()


fun Context.getActivity(): ComponentActivity = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> throw IllegalStateException("No parent activity found.")
}