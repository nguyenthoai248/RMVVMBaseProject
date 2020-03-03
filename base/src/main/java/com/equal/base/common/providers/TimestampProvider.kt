package com.equal.base.common.providers

import javax.inject.Inject

/**
 * Created by Thoai Nguyen on 3/3/20.
 *
 * Class to be able to test timestamp related features. Inject this instead of using System.currentTimeMillis()
 */
class TimestampProvider @Inject internal constructor() {
    fun currentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}