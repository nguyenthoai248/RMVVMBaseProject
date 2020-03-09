package com.equal.dgwproject.login.domain.repositories

import com.equal.base.domain.common.*
import com.equal.dgwproject.login.data.LimitOffsetBoundaryCallback
import com.equal.dgwproject.login.data.LoginApiDataSource
import com.equal.dgwproject.login.data.LoginDatabaseDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers

/**
 * Created by Thoai Nguyen on 3/9/20.
 */
/**
 * Created by Thoai Nguyen on 3/9/20.
 */
class LoginRepositoryImpl(private val apiSource: LoginApiDataSource,
                          private val databaseSource: LoginDatabaseDataSource
): LoginRepository {
    override fun login(email: String, password: String): ResultState {
        val processor =
            PublishProcessor.create<State>()
        val disposables = CompositeDisposable()

        val callback =
            BoundaryCallback(
                apiSource,
                databaseSource,
                processor,
                disposables
            )
        return ResultState(
            processor.hide().doOnCancel { disposables.clear() },
            databaseSource.getDataSourceFactory().map { it as Entity },
            callback
        )
    }

    private class BoundaryCallback(
        private val apiSource: LoginApiDataSource,
        private val databaseSource: LoginDatabaseDataSource,
        private val processor: PublishProcessor<State>,
        private val disposables: CompositeDisposable
    ) : LimitOffsetBoundaryCallback(
        LoginRepository.PAGE_SIZE,
        LoginRepository.PAGE_SIZE * LoginRepository.DEFAULT_INITIAL_PAGE_MULTIPLIER
    ) {

        override fun loadingState(loading: Boolean) = processor.onNext(
            LoadingState(
                loading
            )
        )

        override fun request(offset: String, limit: String) {
            disposables.add(
                apiSource.login(offset, limit)
                    .subscribeOn(Schedulers.io())
                    .subscribe({ entity ->
//                        processor.onNext(LoadingState(false))
//                        if (offset + limit >= entity.totalCount) {
//                            endOfList()
//                        }
//                        processor.onNext(TotalCountState(entity.totalCount))
//                        databaseSource.saveAll(entity.albums)
                    }) {
                        processor.onNext(
                            LoadingState(
                                false
                            )
                        )
                        processor.onNext(
                            ErrorState(
                                it
                            )
                        )
                    }
            )
        }
    }
}