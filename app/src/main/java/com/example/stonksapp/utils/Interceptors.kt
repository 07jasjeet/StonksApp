package com.example.stonksapp.utils

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import com.example.stonksapp.BuildConfig
import okhttp3.CacheControl
import java.util.concurrent.TimeUnit

class CustomInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()

        val newUrl = request.url.run {
            newBuilder()
                .addQueryParameter("apikey", BuildConfig.API_KEY)
                .build()
        }

        request = request
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(request)
    }
}

class CacheInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request
            .newBuilder()
            .cacheControl(
                CacheControl.Builder()
                    // Data is updated only once per day.\
                    .maxStale(1, TimeUnit.HOURS)
                    .build()
            )
            .build()

        return chain.proceed(request)
    }
}