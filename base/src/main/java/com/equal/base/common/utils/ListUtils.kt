package com.equal.base.common.utils

import java.util.*

/**
 * Created by Thoai Nguyen on 3/3/20.
 */
object ListUtils {
    /**
     * Returns a new list containing the second list appended to the
     * first list.
     *
     * @param list1  the first list
     * @param list2  the second list
     * @return  a new list containing the union of those lists
     */
    fun <T> union(
        list1: List<T>,
        list2: List<T>
    ): List<T> {
        return object : ArrayList<T>() {
            init {
                addAll(list1)
                addAll(list2)
            }
        }
    }

    fun <T> isNotEmpty(list: List<T>): Boolean {
        return !list.isEmpty()
    }
}