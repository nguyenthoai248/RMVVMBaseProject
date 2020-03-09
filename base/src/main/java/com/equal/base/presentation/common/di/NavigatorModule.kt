package com.equal.base.presentation.common.di

import com.equal.base.presentation.common.navigator.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Thoai Nguyen on 3/9/20.
 */

@Module
abstract class NavigatorModule {
    @Binds
    internal abstract fun bindNavigatorFactory(factory: NavigatorFactoryImpl): NavigatorFactory

    @Binds
    @IntoMap
    @NavigatorKey(LoginNavigator::class)
    abstract fun bindsLoginNavigator(navigator: LoginNavigatorImpl): Navigator
}