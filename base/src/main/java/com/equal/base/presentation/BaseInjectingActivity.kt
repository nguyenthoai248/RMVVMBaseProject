package com.equal.base.presentation

import android.os.Bundle

import com.equal.base.common.Preconditions

/**
 * Created by Thoai Nguyen on 3/3/2020.
 */
abstract class BaseInjectingActivity<Component> : BaseActivity() {

    private var component: Component? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        component = createComponent()
        onInject(component!!)

        super.onCreate(savedInstanceState)
    }

    fun getComponent(): Component {
        return Preconditions.get<Component>(component)
    }

    protected abstract fun onInject(component: Component)

    protected abstract fun createComponent(): Component
}