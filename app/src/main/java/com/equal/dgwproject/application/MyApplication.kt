package com.equal.dgwproject.application

import android.app.Application
import androidx.annotation.CallSuper
import com.equal.dgwproject.BuildConfig
import com.equal.dgwproject.R
import timber.log.Timber
import timber.log.Timber.DebugTree
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


/**
 * Created by Thoai Nguyen on 3/4/2020.
 */
class MyApplication : Application() {

    private var component: AppComponent? = null

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        getComponent().inject(this)
        installFonts()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    fun getComponent(): AppComponent {
        if (component == null) {
            component = DaggerAppComponent.builder().application(this).build()
        }
        return component as AppComponent
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
