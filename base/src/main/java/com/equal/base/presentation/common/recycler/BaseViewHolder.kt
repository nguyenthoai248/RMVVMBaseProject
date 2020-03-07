package com.equal.base.presentation.common.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.equal.base.presentation.common.Action
import io.reactivex.subjects.PublishSubject

/**
 * Created by Thoai Nguyen on 3/7/2020.
 */

abstract class BaseViewHolder<in T : RecyclerState>(view: View) : RecyclerView.ViewHolder(view) {
    val actionStream = PublishSubject.create<Action>()

    abstract fun bindTo(data: T)
}