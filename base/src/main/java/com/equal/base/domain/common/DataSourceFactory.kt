package com.equal.base.domain.common

/**
 * Created by Thoai Nguyen on 3/7/2020.
 */

import androidx.paging.DataSource

abstract class DataSourceFactory<Key, Value>(var totalCount: Int) : DataSource.Factory<Key, Value>()