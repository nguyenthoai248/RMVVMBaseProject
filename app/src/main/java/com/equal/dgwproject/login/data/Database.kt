package com.equal.dgwproject.login.data

import android.app.Application
import io.objectbox.BoxStore

/**
 * Created by Thoai Nguyen on 3/9/20.
 */

class Database(private var app: Application) {

    internal val boxStore: BoxStore by lazy(mode = LazyThreadSafetyMode.NONE) {
        MyObjectBox.builder().androidContext(app).build()
    }
}