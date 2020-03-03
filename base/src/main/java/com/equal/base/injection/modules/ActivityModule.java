package com.equal.base.injection.modules;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.equal.base.injection.qualifiers.ForActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Thoai Nguyen on 3/4/20.
 */
@Module
public final class ActivityModule {

    @NonNull
    private final AppCompatActivity activity;

    public ActivityModule(@NonNull final AppCompatActivity activity) {
        this.activity = activity;
    }

    @ForActivity
    @Provides
    Context provideContext() {
        return activity;
    }

    @Provides
    FragmentManager provideFragmentManager(@NonNull final AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }
}