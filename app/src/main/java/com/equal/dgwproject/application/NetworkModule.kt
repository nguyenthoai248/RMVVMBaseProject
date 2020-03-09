package com.equal.dgwproject.application

import android.content.Context
import com.equal.base.BuildConfig
import com.equal.base.data.Api
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapterFactory
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Thoai Nguyen on 3/4/2020.
 */
@Module
class NetworkModule {

    private val baseUrl: String = "https://jsonplaceholder.typicode.com/"
    private val apiKey: String = "api key"

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Singleton
    @Provides
    fun providePicasso(context: Context): Picasso {
        val downloader = OkHttp3Downloader(context, Long.MAX_VALUE)
        val picasso = Picasso.Builder(context).downloader(downloader).build()
        Picasso.setSingletonInstance(picasso)
        return picasso
    }

    @Singleton
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor> {

        val interceptors = arrayListOf<Interceptor>()

        val keyInterceptor = Interceptor { chain ->

            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            return@Interceptor chain.proceed(request)
        }

        interceptors.add(keyInterceptor)
        return interceptors
    }

    @Singleton
    @Provides
    fun provideRetrofit(interceptors: ArrayList<Interceptor>): Retrofit {

        val clientBuilder = OkHttpClient.Builder()
        if (!interceptors.isEmpty()) {
            interceptors.forEach { interceptor ->
                clientBuilder.addInterceptor(interceptor)
            }
        }
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            clientBuilder.addInterceptor(loggingInterceptor)
        }

        return Retrofit.Builder()
            .client(clientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }
}