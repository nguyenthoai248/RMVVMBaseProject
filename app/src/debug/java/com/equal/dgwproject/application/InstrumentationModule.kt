package com.equal.dgwproject.application

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor

import android.content.Context
import android.util.Log

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import com.equal.dgwproject.application.NetworkModule.AppInterceptor
import com.equal.dgwproject.application.NetworkModule.NetworkInterceptor
import com.equal.base.injection.qualifiers.ForApplication
import okhttp3.logging.HttpLoggingInterceptor

@Module
object InstrumentationModule {

    @Provides
    @NetworkInterceptor
    @IntoSet
    @Singleton
    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message -> Log.d("OkHttp", message) }
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @NetworkInterceptor
    @IntoSet
    @Singleton
    internal fun provideStethoInterceptor(): StethoInterceptor {
        return StethoInterceptor()
    }

    @Provides
    @AppInterceptor
    @IntoSet
    @Singleton
    internal fun provideChuckInterceptor(@ForApplication context: Context): ChuckInterceptor {
        return ChuckInterceptor(context)
    }
}
