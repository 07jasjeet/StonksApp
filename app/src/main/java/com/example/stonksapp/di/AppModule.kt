package com.example.stonksapp.di

import android.content.Context
import com.example.stonksapp.repository.mainrepository.MainRepository
import com.example.stonksapp.repository.mainrepository.MainRepositoryImpl
import com.example.stonksapp.repository.preferences.AppPreferences
import com.example.stonksapp.repository.preferences.AppPreferencesImpl
import com.example.stonksapp.service.ApiService
import com.example.stonksapp.utils.CacheInterceptor
import com.example.stonksapp.utils.CustomInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesAppPreferences(@ApplicationContext context: Context) : AppPreferences =
        AppPreferencesImpl(context)

    @Provides
    @Singleton
    fun providesOkHttp(cache: Cache) : OkHttpClient = OkHttpClient.Builder()
        .cache(cache)   // Caching
        .addInterceptor(CacheInterceptor())
        .addInterceptor(CustomInterceptor())
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesCache(@ApplicationContext context: Context) =
        Cache(context.cacheDir, (5 * 1024 * 1024).toLong())

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    fun providesMainRepository(apiService: ApiService): MainRepository =
        MainRepositoryImpl(apiService)
}