package com.equal.base.data.store

import io.reactivex.Observable
import polanski.option.Option

/**
 * Created by Thoai Nguyen on 3/4/20.
 */
/**
 * Interface for any kind of reactive store.
 */
interface ReactiveStore<Key, Value> {
    fun storeSingular(model: Value)
    fun storeAll(modelList: List<Value>)
    fun replaceAll(modelList: List<Value>)
    fun getSingular(key: Key): Observable<Option<Value>>
    val all: Observable<Option<List<Value>>>
}