package com.equal.base.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * Created by Thoai Nguyen on 3/3/2020.
 */

abstract class BaseInjectingFragment : Fragment() {
    override fun onAttach(context: Context) {
        onInject()

        super.onAttach(context)
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    abstract fun onInject()

    @LayoutRes
    protected abstract fun getLayoutId(): Int
}