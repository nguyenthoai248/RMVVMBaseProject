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
@Component(modules = [ApplicationModule::class, NetworkModule::class, DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(app: MyApplication)

    fun createHomeActivityComponent(module: ActivityModule): HomeActivityComponent

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent
        @BindsInstance
        fun application(app: MyApplication): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun dataModule(mData: DataModule): Builder
        fun viewmodelModule(viewModelModule: ViewModelModule): Builder
        fun instrumentModule(instrumentModule: InstrumentationModule): Builder
    }
}