package com.equal.base.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Thoai Nguyen on 3/4/20.
 */
internal class DiffUtilCallback(
    private val oldItems: List<DisplayableItem<*>>,
    private val newItems: List<DisplayableItem<*>>,
    private val comparator: ItemComparator
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return comparator.areItemsTheSame(
            oldItems[oldItemPosition],
            newItems[newItemPosition]
        )
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return comparator.areContentsTheSame(
            oldItems[oldItemPosition],
            newItems[newItemPosition]
        )
    }
}
