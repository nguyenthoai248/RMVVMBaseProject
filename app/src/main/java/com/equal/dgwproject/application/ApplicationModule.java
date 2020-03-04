package com.equal.dgwproject.application;

import android.content.Context;

import com.equal.base.injection.qualifiers.ForApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Thoai Nguyen on 3/4/2020.
 */
@Module
public class ApplicationModule {

    @ForApplication
    @Provides
    Context provideApplicationContext(MyApplication app) {
        return app.getApplicationContext();
    }
}

