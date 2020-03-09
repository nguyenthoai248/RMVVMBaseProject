package com.equal.dgwproject.login.data

import android.util.Log
import androidx.paging.PositionalDataSource
import io.objectbox.query.Query
import io.objectbox.reactive.DataObserver
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by Thoai Nguyen on 3/9/20.
 */


class LimitOffsetDataSource<T>(
    private val query: Query<T>,
    private val dataHashCode: AtomicInteger
) : PositionalDataSource<T>(), DataObserver<List<T>> {

    override fun onData(data: List<T>) {
        val hashCode = data.hashCode()
        if (hashCode != dataHashCode.getAndSet(hashCode) && data.isNotEmpty()) {
            invalidate()
        }
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<T?>
    ) {

        val totalCount = countItems()
        if (totalCount == 0) {
            callback.onResult(emptyList(), 0, 0)
            return
        }
        Log.d(
            "LimitOffsetDataSource",
            "loadInitial: ${params.requestedStartPosition}, ${params.requestedLoadSize}, $totalCount"
        )

        // bound the size requested, based on known count
        val firstLoadPosition = computeInitialLoadPosition(params, totalCount)
        val firstLoadSize = computeInitialLoadSize(params, firstLoadPosition, totalCount)

        val list = loadRange(firstLoadPosition, firstLoadSize)
        if (list.size == firstLoadSize) {
            callback.onResult(list, firstLoadPosition, totalCount)
        } else {
            // null list, or size doesn't match request - DB modified between count and load
            invalidate()
        }
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<T?>
    ) {
        val list = loadRange(params.startPosition, params.loadSize)
        callback.onResult(list)
    }

    /**
     * Count number of rows query can return
     */
    private fun countItems(): Int = query.count().toInt()

    /**
     * Return the rows from startPos to startPos + loadCount
     */
    private fun loadRange(startPosition: Int, loadCount: Int): List<T> {
        Log.d("LimitOffsetDataSource", "loadRange: $startPosition, $loadCount")
        return query.find(startPosition.toLong(), loadCount.toLong())
    }
}