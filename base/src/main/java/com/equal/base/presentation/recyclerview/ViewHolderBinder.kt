package com.equal.base.presentation.recyclerview

import androidx.recyclerview.widget.RecyclerView.*

/**
 * Created by Thoai Nguyen on 3/4/20.
 *
 * Populates a [ViewHolder] with the model details.
 *
 */
interface ViewHolderBinder {

    /**
     * Populates the passed [ViewHolder] with the details of the passed [DisplayableItem].
     */
    fun bind(viewHolder: ViewHolder, item: DisplayableItem<*>)
}
