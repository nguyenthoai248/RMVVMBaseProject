package com.equal.base.presentation.common.navigator

import android.os.Bundle
import androidx.navigation.NavController
import javax.inject.Inject

/**
 * Created by Thoai Nguyen on 3/9/20.
 */

class LoginNavigatorImpl @Inject constructor() : LoginNavigator{
    var mToken: String? = null

    override fun setToken(token: String) {
        this.mToken = token
    }

    override fun getToken(): String? {
//        return LoginFragmentArgs.fromBundle(arguments!!).albumId.toLong()
        return ""
    }

    override fun launchFragment(nav: NavController) {
//        nav.navigate(LoginFragmentDirections.actionLogin(mToken))
    }
}