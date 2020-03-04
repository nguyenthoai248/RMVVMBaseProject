package com.equal.dgwproject.application;

import android.app.Application;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import com.equal.dgwproject.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Thoai Nguyen on 3/4/2020.
 */
public class MyApplication extends Application {

    private ApplicationComponent component;

    @CallSuper
    @Override
    public void onCreate() {
        super.onCreate();
        getComponent().inject(this);
        installFonts();
    }

    @NonNull
    public ApplicationComponent getComponent() {
        if (component == null) {
            component = DaggerApplicationComponent.builder().application(this).build();
        }
        return component;
    }

    private void installFonts() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/CalibreApp-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
