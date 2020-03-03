package com.equal.base.data.store

import androidx.annotation.NonNull
import com.equal.base.data.store.Store.DiskStore
import com.equal.base.data.store.Store.MemoryStore
import io.reactivex.Maybe

/**
 * Created by Thoai Nguyen on 3/3/20.
 *
 * Interface for any type of store. Don't implement this directly,
 * use [MemoryStore] or [DiskStore] so it is more
 * descriptive.
 */
interface Store<Key, Value> {
    fun putSingular(value: Value)
    fun putAll(valueList: List<Value>)
    fun clear()
    fun getSingular(key: Key): Maybe<Value>
    @NonNull
    fun getAll(): Maybe<List<Value>>

    /**
     * More descriptive interface for memory based stores.
     */
    interface MemoryStore<Key, Value> : Store<Key, Value>

    /**
     * More descriptive interface for disk based stores.
     */
    interface DiskStore<Key, Value> : Store<Key, Value>
}