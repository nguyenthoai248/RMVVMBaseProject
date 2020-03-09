package com.equal.dgwproject.login.presentation

import androidx.annotation.StringRes
import com.equal.base.presentation.ViewState

/**
 * Created by Thoai Nguyen on 3/7/20.
 */

data class LoginViewState(
    val loading: Boolean,
    @StringRes val errorMessage: Int,
    val error: Throwable?
) : ViewState