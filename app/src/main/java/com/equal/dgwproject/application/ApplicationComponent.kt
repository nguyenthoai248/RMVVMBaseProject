package com.equal.dgwproject.application

import com.equal.base.injection.modules.ActivityModule
import com.equal.dgwproject.home.presentation.HomeActivityComponent

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component

/**
 * Created by Thoai Nguyen on 3/4/2020.
 */
@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun inject(app: MyApplication)

    fun createHomeActivityComponent(module: ActivityModule): HomeActivityComponent

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: MyApplication): Builder

        fun build(): ApplicationComponent
    }
}