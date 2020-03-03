package com.equal.base.presentation.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/**
 * Created by Thoai Nguyen on 3/4/20.
 */
//final class DiffUtilCallback extends DiffUtil.Callback {
//
//    @NonNull
//    private final List<DisplayableItem> oldItems;
//
//    @NonNull
//    private final List<DisplayableItem> newItems;
//
//    @NonNull
//    private final ItemComparator comparator;
//
//    DiffUtilCallback(@NonNull final List<DisplayableItem> oldItems,
//                     @NonNull final List<DisplayableItem> newItems,
//                     @NonNull final ItemComparator comparator) {
//        this.oldItems = oldItems;
//        this.newItems = newItems;
//        this.comparator = comparator;
//    }
//
//    @Override
//    public int getOldListSize() {
//        return oldItems.size();
//    }
//
//    @Override
//    public int getNewListSize() {
//        return newItems.size();
//    }
//
//    @Override
//    public boolean areItemsTheSame(final int oldItemPosition, final int newItemPosition) {
//        return comparator.areItemsTheSame(oldItems.get(oldItemPosition),
//                newItems.get(newItemPosition));
//    }
//
//    @Override
//    public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {
//        return comparator.areContentsTheSame(oldItems.get(oldItemPosition),
//                newItems.get(newItemPosition));
//    }
//}
