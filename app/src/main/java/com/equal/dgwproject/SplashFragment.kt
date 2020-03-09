package com.equal.dgwproject

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.fragment.findNavController
import com.equal.base.presentation.BaseInjectingFragment

/**
 * Created by Thoai Nguyen on 3/9/20.
 */

class SplashFragment : BaseInjectingFragment() {

    override fun onInject() {}

    override fun getLayoutId(): Int = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            context?.let {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }, 2500)
    }
}