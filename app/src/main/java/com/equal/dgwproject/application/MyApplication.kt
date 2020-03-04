package com.equal.dgwproject.application

import android.app.Application

import androidx.annotation.CallSuper

import com.equal.dgwproject.R

import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by Thoai Nguyen on 3/4/2020.
 */
class MyApplication : Application() {

    private var component: ApplicationComponent? = null

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        getComponent().inject(this)
        installFonts()
    }

    fun getComponent(): ApplicationComponent {
        if (component == null) {
            component = DaggerApplicationComponent.builder().application(this).build()
        }
        return component
    }

    private fun installFonts() {
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/CalibreApp-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}
