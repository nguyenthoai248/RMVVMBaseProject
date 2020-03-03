package com.equal.base.presentation.utils

import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Thoai Nguyen on 3/3/2020.
 *
 * Creates a one off view model factory for the given view model instance.
 *
 */
class ViewModelUtil @Inject
constructor() {

    fun <T : ViewModel> createFor(viewModel: T): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(viewModel::class.java)) {
                    return viewModel as T
                }
                throw IllegalArgumentException("unexpected viewModel class $modelClass")
            }
        }
    }
}
