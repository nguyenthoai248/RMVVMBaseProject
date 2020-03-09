package com.equal.dgwproject.application

import com.equal.base.injection.modules.ActivityModule
import com.equal.base.presentation.common.di.NavigatorModule
import com.equal.dgwproject.home.presentation.HomeActivityComponent
import com.equal.dgwproject.login.presentation.di.LoginModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Thoai Nguyen on 3/4/2020.
 */
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        ViewModelModule::class,
        NavigatorModule::class,
        LoginModule::class
    ]
)
interface AppComponent {

    fun inject(app: MyApplication)

    fun createHomeActivityComponent(module: ActivityModule): HomeActivityComponent

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
        @BindsInstance
        fun application(app: MyApplication): Builder

        fun networkModule(networkModule: NetworkModule): Builder
        fun dataModule(mDatabase: DatabaseModule): Builder
        fun loginModule(mLoginModule: LoginModule): Builder
    }
}