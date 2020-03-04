package com.equal.base.presentation.recyclerview

/**
 * Created by Thoai Nguyen on 3/4/20.
 */

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

import com.equal.base.common.preconditions.AndroidPreconditions

import java.util.ArrayList

import io.reactivex.Single
import io.reactivex.functions.Consumer

/**
 * Implementation of [RecyclerViewAdapter] for [DisplayableItem].
 *
 *
 */
class RecyclerViewAdapter(
    private val comparator: ItemComparator,
    private val factoryMap: Map<Int, ViewHolderFactory>,
    private val binderMap: Map<Int, ViewHolderBinder>,
    private val androidPreconditions: AndroidPreconditions
) : Adapter<RecyclerView.ViewHolder>() {

    private val modelItems = ArrayList<DisplayableItem<*>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return factoryMap[viewType]?.createViewHolder(parent)!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = modelItems[position]
        binderMap[item.type()]?.bind(holder, item)
    }

    override fun getItemCount(): Int {
        return modelItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return modelItems[position].type()
    }

    /**
     * Updates modelItems currently stored in adapter with the new modelItems.
     *
     * @param items collection to update the previous values
     */
    fun update(items: List<DisplayableItem<*>>) {
        androidPreconditions.assertUiThread()

        if (modelItems.isEmpty()) {
            updateAllItems(items)
        } else {
            updateDiffItemsOnly(items)
        }
    }

    /**
     * Only use for the first update of the adapter, whe it is still empty.
     */
    private fun updateAllItems(items: List<DisplayableItem<*>>) {
        Single.just(items)
            .doOnSuccess { this.updateItemsInModel(it) }
            .subscribe { _ -> notifyDataSetChanged() }
    }

    /**
     * Do not use for first update of the adapter. The method [DiffResult.dispatchUpdatesTo] is significantly slower than [ ][RecyclerViewAdapter.notifyDataSetChanged] when it comes to update all the items in the adapter.
     */
    private fun updateDiffItemsOnly(items: List<DisplayableItem<*>>) {
        // IMPROVEMENT: The diff calculation should happen in the background
        Single.fromCallable { calculateDiff(items) }
            .doOnSuccess { updateItemsInModel(items) }
            .subscribe(Consumer<DiffResult> { this.updateAdapterWithDiffResult(it) })
    }

    private fun calculateDiff(newItems: List<DisplayableItem<*>>): DiffResult {
        return DiffUtil.calculateDiff(DiffUtilCallback(modelItems, newItems, comparator))
    }

    private fun updateItemsInModel(items: List<DisplayableItem<*>>) {
        modelItems.clear()
        modelItems.addAll(items)
    }

    private fun updateAdapterWithDiffResult(result: DiffResult) {
        result.dispatchUpdatesTo(this)
    }
}
