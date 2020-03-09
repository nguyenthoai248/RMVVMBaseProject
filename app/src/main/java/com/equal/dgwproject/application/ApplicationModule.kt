package com.equal.dgwproject.application

import android.app.Application
import android.content.Context

import com.equal.base.injection.qualifiers.ForApplication

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Thoai Nguyen on 3/4/2020.
 */
@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideApplication(app: MyApplication): Application {
        return app
    }

    @Singleton
    @Provides
    fun provideAppContext(app: MyApplication): Context {
        return app.applicationContext
    }
}

