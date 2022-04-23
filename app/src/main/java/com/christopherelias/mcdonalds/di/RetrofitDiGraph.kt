package com.christopherelias.mcdonalds.di

import com.christopherelias.mcdonalds.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDiGraph {

    private fun provideOkHttpBuilder(): OkHttpClient.Builder {
        return HttpClientFactory().create()
    }

    private fun provideClient(): OkHttpClient {
        val clientBuilder = provideOkHttpBuilder()
        clientBuilder
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(loggingInterceptor)
        }

        return clientBuilder.build()
    }

    fun provideRetrofitBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://mcdonalds.trio.dev/")
            .client(provideClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}

internal class HttpClientFactory {

    private val httpClient by lazy { OkHttpClient() }

    fun create(): OkHttpClient.Builder {
        return httpClient.newBuilder()
    }
}