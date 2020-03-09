package com.equal.base.presentation.common.navigator

import android.os.Bundle

/**
 * Created by Thoai Nguyen on 3/9/20.
 */

interface LoginNavigator : Navigator {

    fun setToken(token: String)

    fun getToken(): String?
}