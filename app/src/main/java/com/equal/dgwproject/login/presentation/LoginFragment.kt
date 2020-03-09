package com.equal.dgwproject.login.presentation

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.navigation.fragment.findNavController
import com.equal.base.presentation.BaseInjectingActivity
import com.equal.base.presentation.BaseInjectingFragment
import com.equal.base.presentation.common.navigator.Navigator
import com.equal.dgwproject.R
import com.equal.dgwproject.login.data.LoginViewModel
import com.equal.dgwproject.login.presentation.di.LoginComponent.LoginComponentCreator
import com.equal.dgwproject.login.rmvvm.LoginClickedAction
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by Thoai Nguyen on 3/7/20.
 */
class LoginFragment : BaseInjectingFragment() {

    private lateinit var viewModel: LoginViewModel
    override fun onInject() {
        val activity: BaseInjectingActivity<*> = activity as BaseInjectingActivity<*>
        val componentCreator: LoginComponentCreator = activity.getComponent() as LoginComponentCreator
        componentCreator.createLoginComponent().inject(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModel(viewModelFactory) {
            viewState.observe { state ->
                state.loading.let(::handleLoading)
                state.error?.let { handleFailure(state.errorMessage, it) }
            }
            navigateViewState.observe(::handleNavigate)
        }
        btnLogin.setOnClickListener {
            viewModel.actionStream.onNext(LoginClickedAction("test", "test"))
        }
    }
//
    private fun handleFailure(@StringRes errorMessage: Int, @Suppress("UNUSED_PARAMETER") error: Throwable) {
//        showFailure(errorMessage)
//        { viewModel.actionStream.onNext(GetAlbumAction(true)) }
    }

    private fun handleLoading(loading: Boolean) {
//        if (loading) {
//            progressView.visible()
//        } else {
//            progressView.gone()
//        }
    }

    private fun handleNavigate(navigator: Navigator) {
        navigator.launchFragment(findNavController())
    }
}