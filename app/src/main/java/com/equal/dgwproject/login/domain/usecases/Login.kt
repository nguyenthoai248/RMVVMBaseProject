package com.equal.dgwproject.login.domain.usecases

import com.equal.base.domain.common.ResultState
import com.equal.dgwproject.login.domain.repositories.LoginRepository

/**
 * Created by Thoai Nguyen on 3/7/20.
 */

class Login (private val repository: LoginRepository){
    fun resultState(): ResultState = repository.login("", "")
}