package com.equal.base.injection.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

import com.equal.base.injection.qualifiers.ForActivity

import dagger.Module
import dagger.Provides

/**
 * Created by Thoai Nguyen on 3/4/20.
 */
@Module
class ActivityModule(val activity: AppCompatActivity) {

    @ForActivity
    @Provides
    internal fun provideContext(): Context {
        return activity
    }

    @Provides
    internal fun provideFragmentManager(activity: AppCompatActivity): FragmentManager {
        return activity.supportFragmentManager
    }
}