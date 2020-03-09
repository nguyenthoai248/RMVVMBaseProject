package com.equal.dgwproject.login.presentation.di

import com.equal.base.data.Api
import com.equal.dgwproject.login.data.*
import com.equal.dgwproject.login.domain.repositories.LoginRepository
import com.equal.dgwproject.login.domain.repositories.LoginRepositoryImpl
import com.equal.dgwproject.login.domain.usecases.Login
import dagger.Module
import dagger.Provides

/**
 * Created by Thoai Nguyen on 3/8/20.
 */

@Module
class LoginModule {
    @Provides
    fun provideDatabaseSource(albumDao: LoginDao): LoginDatabaseDataSource {
        return LoginDatabaseSource(albumDao)
    }

    @Provides
    fun provideApiSource(api: Api): LoginApiDataSource {
        return LoginApiSource(api)
    }

    @Provides
    fun provideRepository(
        apiSource: LoginApiDataSource,
        databaseSource: LoginDatabaseDataSource
    ): LoginRepository {
        return LoginRepositoryImpl(
            apiSource,
            databaseSource
        )
    }

    @Provides
    fun provideUseCase(
        repository: LoginRepository
    ): Login {
        return Login(repository)
    }
}