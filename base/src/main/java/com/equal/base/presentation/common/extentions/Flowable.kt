package com.equal.base.presentation.common.extentions

/**
 * Created by Thoai Nguyen on 3/7/2020.
 */
import io.reactivex.Flowable

inline fun <T, reified E : T> Flowable<in T>.filterTo(@Suppress("UNUSED_PARAMETER") target: Class<E>): Flowable<out E> =
    this.filter {
        when (it) {
            is E -> true
            else -> false
        }
    }.map { it as E }