package com.equal.base.data.cache

import com.google.auto.value.AutoValue

/**
 * Created by Thoai Nguyen on 3/3/20.
 *
 * Cache entry that contains the object and the creation timestamp.
 */
@AutoValue
internal abstract class CacheEntry<T> {
    abstract fun cachedObject(): T
    abstract fun creationTimestamp(): Long
    @AutoValue.Builder
    internal interface Builder<T> {
        fun cachedObject(`object`: T): Builder<T>
        fun creationTimestamp(creationTimestamp: Long): Builder<T>
        fun build(): CacheEntry<T>
    }

    companion object {
        fun <T> builder(): Builder<T> {
            return AutoValue_CacheEntry.Builder()
        }
    }
}