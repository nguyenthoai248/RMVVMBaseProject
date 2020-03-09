package com.equal.dgwproject.home.presentation

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.equal.base.injection.modules.ActivityModule
import com.equal.base.presentation.BaseInjectingActivity
import com.equal.dgwproject.R
import com.equal.dgwproject.application.MyApplication
import com.equal.dgwproject.login.presentation.LoginFragment
import polanski.option.Option

class HomeActivity  : BaseInjectingActivity<HomeActivityComponent>() {


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Option.ofObj(savedInstanceState).ifNone { this.showLoginFragment() }
    }

    private fun showLoginFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.frame, LoginFragment())
            .commit()
    }

    override fun onInject(@NonNull component: HomeActivityComponent) {
        component.inject(this)
    }

    override val layoutId: Int
        get() {
            return R.layout.activity_home
        }

    @NonNull
    override fun createComponent(): HomeActivityComponent {
        val app = MyApplication::class.java.cast(application)
        val activityModule = ActivityModule(this)
        return app!!.getComponent().createHomeActivityComponent(activityModule)
    }
}
