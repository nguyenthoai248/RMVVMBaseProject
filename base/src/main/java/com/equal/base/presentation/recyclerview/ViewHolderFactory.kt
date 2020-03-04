package com.equal.base.presentation.recyclerview

/**
 * Created by Thoai Nguyen on 3/4/20.
 */

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Instantiates a [RecyclerView.ViewHolder] based on the type.
 *
 *
 */
abstract class ViewHolderFactory protected constructor(protected val context: Context) {

    /**
     * Creates a [RecyclerView.ViewHolder]
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @return the newly created [RecyclerView.ViewHolder]
     */
    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
}
