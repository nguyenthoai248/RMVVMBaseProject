package com.equal.dgwproject.application

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.equal.dgwproject.login.data.LoginViewModel
import com.equal.dgwproject.login.presentation.di.ViewModelFactory
import com.equal.dgwproject.login.presentation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Thoai Nguyen on 3/4/2020.
 */
@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindsAlbumViewModel(viewModel: LoginViewModel): ViewModel
}
