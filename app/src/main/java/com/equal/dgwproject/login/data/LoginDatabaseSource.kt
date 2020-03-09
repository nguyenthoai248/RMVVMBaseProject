package com.equal.dgwproject.login.data

import androidx.paging.DataSource
import com.equal.base.data.LoginDto
import com.equal.dgwproject.extentions.map

/**
 * Created by Thoai Nguyen on 3/9/20.
 */

class LoginDatabaseSource(private val loginDao: LoginDao) : LoginDatabaseDataSource {
    override fun saveToken(token: String) {

    }

    override fun getDataSourceFactory(): DataSource.Factory<Int, LoginDto> = loginDao.getDataSourceFactory().map {
        return@map it.map()
    }
}