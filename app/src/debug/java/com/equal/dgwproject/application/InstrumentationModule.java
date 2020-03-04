package com.equal.dgwproject.application;

import com.equal.base.injection.qualifiers.ForApplication;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.readystatesoftware.chuck.ChuckInterceptor;

import android.content.Context;
import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import com.equal.dgwproject.application.NetworkModule.AppInterceptor;
import com.equal.dgwproject.application.NetworkModule.NetworkInterceptor;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
class InstrumentationModule {

    @Provides
    @NetworkInterceptor
    @IntoSet
    @Singleton
    static HttpLoggingInterceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> Log.d("OkHttp", message))
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @NetworkInterceptor
    @IntoSet
    @Singleton
    static StethoInterceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }

    @Provides
    @AppInterceptor
    @IntoSet
    @Singleton
    static ChuckInterceptor provideChuckInterceptor(@ForApplication Context context) {
        return new ChuckInterceptor(context);
    }
}
