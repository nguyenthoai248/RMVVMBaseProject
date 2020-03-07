package com.equal.base.presentation.common.di

import com.equal.base.presentation.common.navigator.Navigator

/**
 * Created by Thoai Nguyen on 3/7/2020.
 */

interface NavigatorFactory {
    fun <T : Navigator> create(clazz: Class<T>): T
}