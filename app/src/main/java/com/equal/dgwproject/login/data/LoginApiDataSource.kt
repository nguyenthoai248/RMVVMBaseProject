package com.equal.dgwproject.login.data

import com.equal.base.data.LoginDto
import io.reactivex.Single

/**
 * Created by Thoai Nguyen on 3/9/20.
 */

interface LoginApiDataSource {
    fun login(userName: String, pass: String): Single<LoginDto>
}