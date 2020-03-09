package com.equal.dgwproject.login.presentation.di

/**
 * Created by Thoai Nguyen on 3/8/20.
 */

import com.equal.base.injection.scopes.FragmentScope
import com.equal.dgwproject.login.presentation.LoginFragment
import dagger.Subcomponent


@FragmentScope
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    fun inject(fragment: LoginFragment)
    interface LoginComponentCreator {
        fun createLoginComponent(): LoginComponent
    }
}