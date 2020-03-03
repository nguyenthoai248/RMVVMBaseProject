package com.equal.base.data.cache

import com.equal.base.common.providers.TimestampProvider
import com.equal.base.data.store.Store.MemoryStore
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import polanski.option.Option
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Thoai Nguyen on 3/3/2020.
 *
 * Generic memory cache with timeout for the entries.
 */
class Cache<Key, Value> private constructor(
    private val extractKeyFromModel: Function<Value, Key>,
    private val timestampProvider: TimestampProvider,
    private val itemLifespanMs: Option<Long>
) : MemoryStore<Key, Value> {
    private val cache: MutableMap<Key, CacheEntry<Value>> =
        ConcurrentHashMap()

    constructor(
        extractKeyFromModel: Function<Value, Key>,
        timestampProvider: TimestampProvider
    ) : this(extractKeyFromModel, timestampProvider, Option.none<Long>()) {
    }

    constructor(
        extractKeyFromModel: Function<Value, Key>,
        timestampProvider: TimestampProvider,
        timeoutMs: Long
    ) : this(
        extractKeyFromModel,
        timestampProvider,
        Option.ofObj<Long>(timeoutMs)
    ) {
    }

    override fun putSingular(value: Value) {
        Single.fromCallable {
            extractKeyFromModel.apply(
                value
            )
        }
            .subscribeOn(Schedulers.computation())
            .subscribe { key: Key ->
                cache[key] = createCacheEntry(value)
            }
    }

    override fun putAll(values: List<Value>) {
        Observable.fromIterable(values)
            .toMap(
                extractKeyFromModel,
                Function { value: Value -> createCacheEntry(value) }
            )
            .subscribeOn(Schedulers.computation())
            .subscribe { map: Map<Key, CacheEntry<Value>>? ->
                cache.putAll(map!!)
            }
    }

    override fun getSingular(key: Key): Maybe<Value> {
        return Maybe.fromCallable {
            cache.containsKey(
                key
            )
        }
            .filter { isPresent: Boolean? -> isPresent!! }
            .map { _ -> cache[key]!! }
            .filter { cacheEntry: CacheEntry<Value> ->
                notExpired(
                    cacheEntry
                )
            }
            .map { obj: CacheEntry<Value> -> obj.cachedObject() }
            .subscribeOn(Schedulers.computation())
    }

    override fun getAll(): Maybe<List<Value>> {
        return Observable.fromIterable(cache.values)
            .filter { cacheEntry: CacheEntry<Value> ->
                notExpired(
                    cacheEntry
                )
            }
            .map { obj: CacheEntry<Value> -> obj.cachedObject() }
            .toList()
            .filter { obj: List<Value> -> obj.isNotEmpty() }
            .subscribeOn(Schedulers.computation())
    }

    override fun clear() {
        cache.clear()
    }

    private fun createCacheEntry(value: Value): CacheEntry<Value> {
        return CacheEntry.builder<Value>().cachedObject(value)
            .creationTimestamp(timestampProvider.currentTimeMillis())
            .build()
    }

    private fun notExpired(cacheEntry: CacheEntry<Value>): Boolean {
        return itemLifespanMs.match(
            { lifespanMs: Long -> cacheEntry.creationTimestamp() + lifespanMs > timestampProvider.currentTimeMillis() }  // When lifespan was not set the items in the cache never expire
        ) { true }
    }
}