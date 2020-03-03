package com.equal.base.data.store

import com.equal.base.data.store.Store.MemoryStore
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import polanski.option.Option
import polanski.option.function.Action1
import polanski.option.function.Func1
import java.util.*

/**
 * Created by Thoai Nguyen on 3/4/20.
 */
/**
 * This reactive store has only a memory cache as form of storage.
 */
class MemoryReactiveStore<Key, Value>(
    extractKeyFromModel: Func1<Value, Key>,
    cache: MemoryStore<Key, Value>
) : ReactiveStore<Key, Value> {
    private val cache: MemoryStore<Key, Value>
    private val extractKeyFromModel: Func1<Value, Key>
    private val allSubject: Subject<Option<List<Value>>>
    private val subjectMap: MutableMap<Key, Subject<Option<Value>>> =
        HashMap()

    override fun storeSingular(model: Value) {
        val key = extractKeyFromModel.call(model)
        cache.putSingular(model)
        getOrCreateSubjectForKey(key).onNext(Option.ofObj(model))
        // One item has been added/updated, notify to all as well
        val allValues = cache.getAll().map { value: List<Value>? -> Option.ofObj(value) }
            .blockingGet(Option.none())
        allSubject.onNext(allValues)
    }

    override fun storeAll(modelList: List<Value>) {
        cache.putAll(modelList)
        allSubject.onNext(Option.ofObj(modelList))
        // Publish in all the existing single item streams.
// This could be improved publishing only in the items that changed. Maybe use DiffUtils?
        publishInEachKey()
    }

    override fun replaceAll(modelList: List<Value>) {
        cache.clear()
        storeAll(modelList)
    }

    override fun getSingular(key: Key): Observable<Option<Value>> {
        return Observable.defer { getOrCreateSubjectForKey(key).startWith(getValue(key)) }
            .observeOn(Schedulers.computation())
    }

    override val all: Observable<Option<List<Value>>>
        get() = Observable.defer { allSubject.startWith(allValues) }
            .observeOn(Schedulers.computation())

    private fun getValue(key: Key): Option<Value> {
        return cache.getSingular(key)
            .map { value: Value ->
                Option.ofObj(value)
            }.blockingGet(Option.none())
    }

    private val allValues: Option<List<Value>>
        private get() = cache.getAll()
            .map { value: List<Value>? -> Option.ofObj(value) }
            .blockingGet(Option.none())

    private fun getOrCreateSubjectForKey(key: Key): Subject<Option<Value>> {
        synchronized(subjectMap) {
            return Option.ofObj(subjectMap[key]).orDefault {
                createAndStoreNewSubjectForKey(
                    key
                )
            }
        }
    }

    private fun createAndStoreNewSubjectForKey(key: Key): Subject<Option<Value>> {
        val processor =
            PublishSubject.create<Option<Value>>().toSerialized()
        synchronized(subjectMap) { subjectMap.put(key, processor) }
        return processor
    }

    /**
     * Publishes the cached data in each independent stream only if it exists already.
     */
    private fun publishInEachKey() {
        var keySet: Set<Key>
        synchronized(subjectMap) { keySet = HashSet(subjectMap.keys) }
        for (key in keySet) {
            val value = cache.getSingular(key)
                .map { value: Value ->
                    Option.ofObj(value)
                }.blockingGet(Option.none())
            publishInKey(key, value)
        }
    }

    /**
     * Publishes the cached value if there is an already existing stream for the passed key. The case where there isn't a stream for the passed key
     * means that the data for this key is not being consumed and therefore there is no need to publish.
     */
    private fun publishInKey(key: Key, model: Option<Value>) {
        var processor: Subject<Option<Value>>
        synchronized(subjectMap) { processor = subjectMap[key]!! }
        Option.ofObj(processor)
            .ifSome(Action1<Subject<Option<Value>>> { it: Subject<Option<Value>> ->
                it.onNext(model)
            })
    }

    init {
        allSubject =
            PublishSubject.create<Option<List<Value>>>()
                .toSerialized()
        this.cache = cache
        this.extractKeyFromModel = extractKeyFromModel
    }
}