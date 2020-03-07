package com.equal.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.equal.base.R
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import dagger.android.support.DaggerFragment

/**
 * Created by Thoai Nguyen on 3/7/2020.
 */

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var snackbar: Snackbar? = null

    protected fun Disposable.track() {
        compositeDisposable.add(this)
    }

    protected fun <T> Flowable<T>.observe(o: (T) -> Unit) {
        RxLifecycleHandler(this@BaseFragment, this, o)
    }

    abstract fun layoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutId(), container, false)


    inline fun <reified T : ViewModel> viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
        val vm = ViewModelProviders.of(this, factory)[T::class.java]
        vm.body()
        return vm
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }


    protected fun showFailure(errorMessage: Int, retry: (View) -> Unit) {
        view?.apply {
            if (snackbar?.isShown == true) {
                snackbar!!.dismiss()
            }
            snackbar = Snackbar.make(this, errorMessage, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry) {
                    snackbar = null
                    retry(it)
                }
            snackbar!!.show()
        }
    }
}