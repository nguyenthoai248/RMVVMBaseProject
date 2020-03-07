package com.equal.base.domain.common

import androidx.paging.DataSource
import androidx.paging.PagedList
import io.reactivex.Flowable

/**
 * Created by Thoai Nguyen on 3/5/2020.
 */
data class ResultState(
    val stateStream: Flowable<State>,
    val factory: DataSource.Factory<Int, Entity>,
    val boundaryCallback: PagedList.BoundaryCallback<Entity>
)

sealed class State
data class LoadingState(val loading: Boolean) : State()
data class TotalCountState(val totalCount: Int) : State()
data class ErrorState(val throwable: Throwable) : State()