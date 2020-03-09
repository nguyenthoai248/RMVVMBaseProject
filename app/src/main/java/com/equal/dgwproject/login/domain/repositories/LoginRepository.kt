package com.equal.dgwproject.login.domain.repositories

import com.equal.base.domain.common.ResultState

/**
 * Created by Thoai Nguyen on 3/7/20.
 */

interface LoginRepository {
    fun login(email: String, password: String): ResultState

    companion object {
        const val PAGE_SIZE = 10
        const val DEFAULT_INITIAL_PAGE_MULTIPLIER = 3
    }
}