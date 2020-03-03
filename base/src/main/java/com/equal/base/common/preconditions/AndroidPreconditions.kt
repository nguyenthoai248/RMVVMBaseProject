package com.equal.base.common.preconditions

import android.os.Looper
import javax.inject.Inject

/**
 * Created by Thoai Nguyen on 3/3/20.
 */
class AndroidPreconditions @Inject internal constructor() {
    /**
     * Asserts that the current thread is a worker thread.
     */
    fun assertWorkerThread() {
        check(!isMainThread) { "This task must be run on a worker thread and not on the Main thread." }
    }

    /**
     * Asserts that the current thread is the Main Thread.
     */
    fun assertUiThread() {
        check(isMainThread) { "This task must be run on the Main thread and not on a worker thread." }
    }

    /**
     * Returns whether the current thread is the Android main thread
     * @return true if the current thread is the main thread, otherwise; false.
     */
    val isMainThread: Boolean
        get() = Looper.getMainLooper().thread == Thread.currentThread()
}