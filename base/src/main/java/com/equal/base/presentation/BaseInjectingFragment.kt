package com.equal.base.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Thoai Nguyen on 3/3/2020.
 */

abstract class BaseInjectingFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var snackbar: Snackbar? = null

    protected fun Disposable.track() {
        compositeDisposable.add(this)
    }

    protected fun <T> Flowable<T>.observe(o: (T) -> Unit) {
        RxLifecycleHandler(this@BaseInjectingFragment, this, o)
    }

    override fun onAttach(context: Context) {
        onInject()

        super.onAttach(context)
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    inline fun <reified T : ViewModel> viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
        val vm = ViewModelProviders.of(this, factory)[T::class.java]
        vm.body()
        return vm
    }

    abstract fun onInject()

    @LayoutRes
    protected abstract fun getLayoutId(): Int
}