package com.equal.base.presentation.common.recycler

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Thoai Nguyen on 3/7/2020.
 */

abstract class BaseAdapter(diff: DiffUtil.ItemCallback<RecyclerState>) :
    PagedListAdapter<RecyclerState, BaseViewHolder<*>>(diff) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.getType() ?: 0
    }
}