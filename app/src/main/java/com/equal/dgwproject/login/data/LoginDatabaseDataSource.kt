package com.equal.dgwproject.login.data

import androidx.paging.DataSource
import com.equal.base.data.LoginDto

/**
 * Created by Thoai Nguyen on 3/9/20.
 */

interface LoginDatabaseDataSource {

    fun saveToken(token: String)

    fun getDataSourceFactory(): DataSource.Factory<Int, LoginDto>
}