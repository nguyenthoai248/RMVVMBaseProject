package com.equal.base.presentation.common.extentions

/**
 * Created by Thoai Nguyen on 3/7/2020.
 */
import io.reactivex.Observable

inline fun <T, reified E : T> Observable<in T>.filterTo(
    @Suppress("UNUSED_PARAMETER") target: Class<E>
): Observable<out E> = this.filter {
    when (it) {
        is E -> true
        else -> false
    }
}.map { it as E }