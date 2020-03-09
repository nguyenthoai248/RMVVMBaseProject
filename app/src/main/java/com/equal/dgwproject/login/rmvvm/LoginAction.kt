package com.equal.dgwproject.login.rmvvm

import com.equal.base.presentation.common.Action

/**
 * Created by Thoai Nguyen on 3/7/20.
 */
sealed class LoginAction: Action
class LoginClickedAction(val userName: String, val password: String) : LoginAction()