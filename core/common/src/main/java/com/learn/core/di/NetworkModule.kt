package com.learn.core.di

import com.learn.core.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org"
        private const val TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMDJkOTJkNDgzMzg2ZTI1YzJjOTUzYWZmZmZkZGNlYiIsInN1YiI6IjY1OGMwZjQ4NjcyOGE4NmQyZDI4MTExMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.97d5gHuGv8D0e3fmviraZr4ovxzu6lBbe19zoMrGaUM"
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
       /* clientBuilder.addInterceptor(Interceptor { chain ->
            val originalResponse = chain.proceed(chain.request())
            val cacheControl = originalResponse.header("Cache-Control")

            originalResponse.newBuilder()
                .header("Cache-Control", cacheControl ?: "public, max-age=2592000")
                .build()
        })*/
        clientBuilder.addInterceptor(Interceptor { chain ->
            val originalRequest = chain.request()
            // Create a new request with the added Authorization header
            val newRequest = originalRequest.newBuilder()
                .header("Authorization", TOKEN) // Assuming it's a Bearer token
                .build()
            // Proceed with the new request
            chain.proceed(newRequest)
        })
        clientBuilder.addInterceptor(interceptor)
        return clientBuilder.build()
    }

    @Provides
    fun provideMoshi(
        // Potential dependencies of this type
    ): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient,moshi:Moshi) =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
}