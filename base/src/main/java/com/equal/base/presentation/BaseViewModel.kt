package com.equal.base.presentation

import androidx.lifecycle.ViewModel
import com.equal.base.presentation.common.Action
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Thoai Nguyen on 3/7/2020.
 */

abstract class BaseViewModel : ViewModel() {
    val actionStream = PublishSubject.create<Action>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun Disposable.track() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}