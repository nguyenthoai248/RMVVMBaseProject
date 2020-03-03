package com.equal.base.common.rx

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import polanski.option.Option
import polanski.option.OptionUnsafe

/**
 * Created by Thoai Nguyen on 3/3/20.
 *
 * Filters out all Option of NONE if any, but if Some, then unwraps and returns the value.
 */
class UnwrapOptionTransformer<T> :
    ObservableTransformer<Option<T>, T> {
    override fun apply(upstream: Observable<Option<T>>): ObservableSource<T> {
        return upstream.filter { obj: Option<T> -> obj.isSome }
            .map { option: Option<T>? ->
                OptionUnsafe.getUnsafe(
                    option!!
                )
            }
    }

    companion object {
        fun <T> create(): UnwrapOptionTransformer<T> {
            return UnwrapOptionTransformer()
        }
    }
}