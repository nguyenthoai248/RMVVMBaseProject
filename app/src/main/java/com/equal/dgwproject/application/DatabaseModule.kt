package com.equal.dgwproject.application

import android.app.Application
import com.equal.dgwproject.login.data.Database
import com.equal.dgwproject.login.data.LoginDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Thoai Nguyen on 3/4/2020.
 */
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(app: Application) = Database(app)

    @Singleton
    @Provides
    fun provideAlbumDao(database: Database) = LoginDao(database)
}
