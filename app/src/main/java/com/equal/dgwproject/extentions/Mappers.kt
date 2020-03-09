package com.equal.dgwproject.extentions

import com.equal.base.data.LoginDto
import com.equal.dgwproject.login.data.LoginData

/**
 * Created by Thoai Nguyen on 3/9/20.
 */

fun LoginData.map() = LoginDto(
    token = token,
    userId = userId,
    title = title
)