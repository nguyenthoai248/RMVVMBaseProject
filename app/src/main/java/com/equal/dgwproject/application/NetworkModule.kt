package com.equal.dgwproject.application

import com.equal.base.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapterFactory

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Thoai Nguyen on 3/4/2020.
 */
@Module(includes = [InstrumentationModule::class])
internal object NetworkModule {

    private val API_URL = "API_URL"

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    internal annotation class AppInterceptor

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    internal annotation class NetworkInterceptor

    @Provides
    @Singleton
    fun provideN26Api(@Named(API_URL) baseUrl: String, gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Named(API_URL)
    fun provideBaseUrl(): String {
        return BuildConfig.N26_FAKE_API_URL
    }

    @Provides
    @Singleton
    fun provideGson(typeAdapters: Set<TypeAdapterFactory>): Gson {
        val builder = GsonBuilder()

        for (factory in typeAdapters) {
            builder.registerTypeAdapterFactory(factory)
        }

        return builder.create()
    }

    @Provides
    @Singleton
    fun provideApiOkHttpClient(
        @AppInterceptor appInterceptor: Set<Interceptor>,
        @NetworkInterceptor networkInterceptor: Set<Interceptor>
    ): OkHttpClient {
        val okBuilder = OkHttpClient.Builder()
        okBuilder.interceptors().addAll(appInterceptor)
        okBuilder.networkInterceptors().addAll(networkInterceptor)

        return okBuilder.build()
    }
}
